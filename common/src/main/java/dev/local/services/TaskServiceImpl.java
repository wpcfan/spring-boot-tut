package dev.local.services;

import dev.local.domain.Task;
import dev.local.domain.User;
import dev.local.repositories.TaskRepository;
import dev.local.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    @Autowired
    TaskServiceImpl(
            TaskRepository repository) {
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
    public List<Task> findRelated(String userId) {
        return repository.findByParticipantIdsContaining(userId);
    }

    @Override
    public List<Task> findByListId(String taskListId) {
        return repository.findByTaskListId(taskListId);
    }

    @Override
    public Task findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public Task update(Task task) {
        repository.save(task);
        return task;
    }
}
