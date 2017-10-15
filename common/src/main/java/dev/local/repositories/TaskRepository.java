package dev.local.repositories;

import dev.local.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String>{
    List<Task> findByParticipantIdsContaining(String userId);
    List<Task> findByTaskListId(String taskListId);
}
