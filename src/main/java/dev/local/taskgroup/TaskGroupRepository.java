package dev.local.taskgroup;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 任务组存储
 */
@Repository
public interface TaskGroupRepository extends MongoRepository<TaskGroup, String> {}
