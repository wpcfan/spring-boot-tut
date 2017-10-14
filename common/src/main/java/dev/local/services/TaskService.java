package dev.local.services;

import dev.local.domain.Task;

import java.util.List;

public interface TaskService {
    Task add(Task task);
    Task delete(String id);
    List<Task> findRelated(String userId);
    List<Task> findByListId(String taskListId);
    Task findById(String id);
    Task update(Task task);
}
