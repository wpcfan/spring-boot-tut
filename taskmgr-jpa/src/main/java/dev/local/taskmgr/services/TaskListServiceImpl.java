package dev.local.taskmgr.services;

import dev.local.taskmgr.domain.TaskList;
import dev.local.taskmgr.repositories.TaskListRepository;
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

    @Override
    public TaskList add(final TaskList list) {
        return repository.save(list);
    }

    @Override
    public TaskList delete(final Long id) {
        final TaskList taskList = repository.findOne(id);
        repository.delete(id);
        return taskList;
    }

    @Override
    public TaskList findById(final Long id) {
        return repository.findOne(id);
    }

    @Override
    public TaskList update(final TaskList list) {
        return repository.save(list);
    }

    @Override
    public List<TaskList> findByProjectId(final Long projectId) {
        return repository.findByProjectId(projectId);
    }

    @Override
    public List<TaskList> swapOrder(final Long srcTaskListId, final Long targetTaskListId) {
        final TaskList src = findById(srcTaskListId);
        final TaskList target = findById(targetTaskListId);
        final TaskList savedSrc = repository.save(src.withOrder(target.getOrder()));
        final TaskList savedTarget = repository.save(target.withOrder(src.getOrder()));
        return Arrays.asList(savedSrc, savedTarget);
    }
}
