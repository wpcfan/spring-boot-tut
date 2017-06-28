package dev.local.repositories;

import dev.local.domain.TaskList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 任务组存储
 */
@Repository
public interface TaskListRepository extends MongoRepository<TaskList, String> {}
