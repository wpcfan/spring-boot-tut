package dev.local.taskmgr.mongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

import dev.local.taskmgr.mongo.domain.Profile;
import dev.local.taskmgr.mongo.domain.Project;
import dev.local.taskmgr.mongo.domain.TaskList;
import dev.local.taskmgr.mongo.dto.QueryProjectDTO;
import dev.local.taskmgr.mongo.repositories.ProfileRepository;
import dev.local.taskmgr.mongo.repositories.ProjectRepoCustom;
import dev.local.taskmgr.mongo.repositories.ProjectRepository;
import dev.local.taskmgr.mongo.repositories.TaskListRepository;

/**
 * Created by wangpeng on 2017/4/18.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;
    private final TaskListRepository taskListRepository;
    private final ProfileRepository profileRepository;
    private final ProjectRepoCustom projectRepoCustom;

    @Autowired
    public ProjectServiceImpl(
            ProjectRepository repository,
            TaskListRepository taskListRepository,
            ProfileRepository profileRepository,
            ProjectRepoCustom projectRepoCustom){
        this.repository = repository;
        this.taskListRepository = taskListRepository;
        this.profileRepository = profileRepository;
        this.projectRepoCustom = projectRepoCustom;
    }

    @Override
    public Project add(Project project, String username) {
        project.setOwnerId(username);
        project.setMemberIds(Collections.singleton(username));
        Project savedProject = repository.insert(project);
        List<TaskList> lists = taskListRepository.save(getInitLists(savedProject));
        savedProject.getTaskListIds()
                .addAll(lists.stream()
                        .map(TaskList::getId)
                        .collect(Collectors.toSet()
                        )
                );
        Profile profile = profileRepository.findByUsername(username);
        profile.setProjectIdsJoined(Collections.singleton(savedProject.getId()));
        profileRepository.save(profile);
        return repository.save(savedProject);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public Page<QueryProjectDTO> findRelated(String userId, Pageable pageable) {
//        return repository.findRelated(userId, enabled, archived, pageable);
        return projectRepoCustom.getJoinedProjectsWithUsers(userId, pageable);
    }

    @Override
    public Project findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public Project update(Project project) {
        return repository.save(project);
    }

    @Override
    public Project inviteMembers(String id, List<String> memberIds) {
        Project project = findById(id);
        project.getMemberIds().addAll(memberIds);
        return repository.save(project);
    }

    private List<TaskList> getInitLists(Project savedProject) {
        TaskList plan = TaskList.builder()
                .name("计划")
                .order(0)
                .projectId(savedProject.getId())
                .build();

        TaskList inProgress = TaskList.builder()
                .name("进行中")
                .order(1)
                .projectId(savedProject.getId())
                .build();

        TaskList done = TaskList.builder()
                .name("已完成")
                .order(2)
                .projectId(savedProject.getId())
                .build();

        return asList(plan, inProgress, done);
    }
}
