package dev.local.services;

import dev.local.domain.Profile;
import dev.local.domain.Project;
import dev.local.domain.TaskList;
import dev.local.dto.QueryProjectDTO;
import dev.local.repositories.ProfileRepository;
import dev.local.repositories.ProjectRepoCustom;
import dev.local.repositories.ProjectRepository;
import dev.local.repositories.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static java.util.Arrays.asList;

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
        TaskList plan = new TaskList();
        plan.setName("计划");
        plan.setOrder(0);
        plan.setProjectId(savedProject.getId());
        taskListRepository.insert(plan);
        TaskList inProgress = new TaskList();
        inProgress.setName("进行中");
        inProgress.setOrder(1);
        inProgress.setProjectId(savedProject.getId());
        taskListRepository.insert(inProgress);
        TaskList done = new TaskList();
        done.setName("已完成");
        done.setOrder(2);
        done.setProjectId(savedProject.getId());
        taskListRepository.insert(done);
        savedProject.getTaskListIds().addAll(asList(
                plan.getId(),
                inProgress.getId(),
                done.getId()));
        Profile profile = profileRepository.findByUsername(username);
        profile.setProjectIdsJoined(Collections.singleton(savedProject.getId()));
        profileRepository.save(profile);
        return repository.save(savedProject);
    }

    @Override
    public Project delete(String id) {
        final Project project = repository.findOne(id);
        repository.delete(id);
        return project;
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
        repository.save(project);
        return project;
    }
}
