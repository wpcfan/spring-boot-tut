package dev.local.tasklist;

import dev.local.domain.Project;
import dev.local.domain.TaskList;
import dev.local.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangpeng on 2017/4/18.
 */
@Service
public class MongoTaskListServiceImpl implements TaskListService {

    private TaskListRepository repository;
    private ProjectService projectService;

    @Autowired
    public MongoTaskListServiceImpl(
            TaskListRepository repository,
            ProjectService projectService){
        this.repository = repository;
        this.projectService = projectService;
    }

    @Override
    public TaskList add(TaskList list, String projectId) {
        Project project = projectService.findById(projectId);
        TaskList added = repository.insert(list);
        project.getGroups().add(added);
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
