package dev.local.services;

import dev.local.domain.Project;
import dev.local.domain.TaskList;
import dev.local.repositories.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangpeng on 2017/4/18.
 */
@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository repository;
    private final ProjectService projectService;

    @Autowired
    public TaskListServiceImpl(
            TaskListRepository repository,
            ProjectService projectService){
        this.repository = repository;
        this.projectService = projectService;
    }

    @Override
    public TaskList add(TaskList list, String projectId) {
        Project project = projectService.findById(projectId);
        TaskList added = repository.insert(list);
        project.getTaskListIds().add(added.getId());
        projectService.update(project);
        return added;
    }

    @Override
    public TaskList delete(String id) {
        final TaskList group = repository.findOne(id);
        repository.delete(id);
        return group;
    }

    @Override
    public TaskList findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public TaskList update(TaskList list) {
        return repository.save(list);
    }
}
