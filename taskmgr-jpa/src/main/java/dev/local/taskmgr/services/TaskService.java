package dev.local.taskmgr.services;

import dev.local.taskmgr.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Task add(Task task);
    void delete(Long id);
    List<Task> findTasksByUser(String username);
    Page<Task> findByListId(Long taskListId, Pageable pageable);
    Task findById(Long id);
    Task update(Task task);
    Task toggle(Long id);
    Task move(Long id, Long taskListId);
    List<Task> moveAll(Long srcListId, Long targetListId);
}
