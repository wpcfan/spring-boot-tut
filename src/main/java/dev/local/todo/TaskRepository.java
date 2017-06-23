package dev.local.todo;

import dev.local.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String>{
    List<Task> findByParticipantsContaining(User user);
    List<Task> findByGroupId(String id);
}
