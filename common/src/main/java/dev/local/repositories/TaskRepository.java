package dev.local.repositories;

import dev.local.domain.Task;
import dev.local.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String>{
    List<Task> findByParticipantsContaining(User user);
    List<Task> findByGroupId(String id);
}
