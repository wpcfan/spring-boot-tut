package dev.local.taskmgr.mongo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import dev.local.taskmgr.mongo.domain.Task;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByParticipantIdsContaining(String username);
    Page<Task> findByTaskListId(String taskListId, Pageable pageable);
    List<Task> findByTaskListId(String taskListId);
}
