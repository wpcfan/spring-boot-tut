package dev.local.services;

import dev.local.domain.Project;
import dev.local.domain.TaskList;
import dev.local.repositories.TaskListRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangpeng on 2017/4/18.
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository repository;
    private final ProjectService projectService;

    @Override
    public TaskList add(TaskList list) {
        Project project = projectService.findById(list.getProjectId());
        TaskList added = repository.insert(list);
        project.getTaskListIds().add(added.getId());
        projectService.update(project);
        return added;
    }

    @Override
    public TaskList delete(String id) {
        final TaskList taskList = repository.findOne(id);
        repository.delete(id);
        return taskList;
    }

    @Override
    public TaskList findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public TaskList update(TaskList list) {
        return repository.save(list);
    }

    @Override
    public List<TaskList> findByProjectId(String projectId) {
        return repository.findByProjectId(projectId);
    }

    @Override
    public List<TaskList> swapOrder(String srcTaskListId, String targetTaskListId) {
        TaskList src = findById(srcTaskListId);
        TaskList target = findById(targetTaskListId);
        final int tempOrder = src.getOrder();
        src.setOrder(target.getOrder());
        repository.save(src);
        target.setOrder(tempOrder);
        repository.save(target);
        return Arrays.asList(src, target);
    }
}
