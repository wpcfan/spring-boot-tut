package dev.local.services;

import dev.local.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Task add(Task task);
    Task delete(String id);
    List<Task> findTasksByUser(String username);
    Page<Task> findByListId(String taskListId, Pageable pageable);
    Task findById(String id);
    Task update(Task task);
    Task toggle(String id);
    Task move(String id, String taskListId);
    List<Task> moveAll(String srcListId, String targetListId);
}
