package dev.local.todo;

import java.util.List;

public interface TaskService {
    Task add(Task task);
    Task delete(String id);
    List<Task> findRelated(String userId);
    List<Task> findByGroupId(String groupId);
    Task findById(String id);
    Task update(Task task);
}
