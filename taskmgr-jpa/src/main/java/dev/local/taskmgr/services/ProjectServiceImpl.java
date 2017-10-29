package dev.local.taskmgr.services;

import dev.local.taskmgr.domain.Profile;
import dev.local.taskmgr.domain.Project;
import dev.local.taskmgr.domain.TaskList;
import dev.local.taskmgr.repositories.ProfileRepository;
import dev.local.taskmgr.repositories.ProjectRepository;
import dev.local.taskmgr.repositories.TaskListRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * Created by wangpeng on 2017/4/18.
 */
@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;
    private final TaskListRepository taskListRepository;

    @Override
    public Project add(Project project, String username) {
        project.getMembers().add(Profile.builder().username(username).build());
        Project savedProject = repository.save(project);
        taskListRepository.save(getInitLists(savedProject));
        return savedProject;
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Page<Project> findRelated(String username, Pageable pageable) {
       return repository.findRelated(username, true, false, pageable);
    }

    @Override
    public Project findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Project update(Project project) {
        return repository.save(project);
    }

    @Override
    public Project inviteMembers(Long id, List<Long> memberIds) {
        Project project = findById(id);
        Stream<Profile> members = memberIds.stream()
                .map(memberId -> Profile.builder().id(memberId).build());
        project.getMembers().addAll(members.collect(Collectors.toList()));
        return repository.save(project);
    }

    private List<TaskList> getInitLists(Project savedProject) {
        TaskList plan = TaskList.builder()
                .name("计划")
                .order(0)
                .project(savedProject)
                .build();

        TaskList inProgress = TaskList.builder()
                .name("进行中")
                .order(1)
                .project(savedProject)
                .build();

        TaskList done = TaskList.builder()
                .name("已完成")
                .order(2)
                .project(savedProject)
                .build();

        return asList(plan, inProgress, done);
    }
}
