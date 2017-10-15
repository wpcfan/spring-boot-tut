package dev.local.repositories;

import dev.local.domain.TaskList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务组存储
 */
@Repository
public interface TaskListRepository extends MongoRepository<TaskList, String> {
    List<TaskList> findByProjectId(String projectId);
}
