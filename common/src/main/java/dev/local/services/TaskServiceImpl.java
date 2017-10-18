package dev.local.services;

import dev.local.domain.Task;
import dev.local.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    @Autowired
    TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task add(Task task) {
        return repository.insert(task);
    }

    @Override
    public Task delete(String id) {
        Task deletedTask = repository.findOne(id);
        repository.delete(id);
        return deletedTask;
    }

    @Override
    public List<Task> findTasksByUser(String username) {
        return repository.findByParticipantIdsContaining(username);
    }

    @Override
    public Page<Task> findByListId(String taskListId, Pageable pageable) {
        return repository.findByTaskListId(taskListId, pageable);
    }

    @Override
    public Task findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public Task update(Task task) {
        return repository.save(task);
    }

    @Override
    public Task toggle(String id) {
        Task task = findById(id);
        task.setCompleted(!task.isCompleted());
        return repository.save(task);
    }

    @Override
    public Task move(String id, String taskListId) {
        Task task = findById(id);
        task.setTaskListId(taskListId);
        return repository.save(task);
    }

    @Override
    public List<Task> moveAll(final String srcListId, final String targetListId) {
        List<Task> srcTasks = repository.findByTaskListId(srcListId);
        for (Task task: srcTasks) {
            task.setTaskListId(targetListId);
        }
        List<Task> targetTasks = repository.findByTaskListId(targetListId);
        for (Task task: targetTasks) {
            task.setTaskListId(srcListId);
        }
        Stream<Task> combinedStream = Stream.of(srcTasks, targetTasks)
                .flatMap(Collection::stream);
        Collection<Task> collectionCombined =
                combinedStream.collect(Collectors.toList());
        return repository.save(collectionCombined);
    }
}
