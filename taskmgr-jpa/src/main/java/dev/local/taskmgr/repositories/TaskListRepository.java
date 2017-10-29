package dev.local.taskmgr.repositories;

import dev.local.taskmgr.domain.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务组存储
 */
@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    List<TaskList> findByProjectId(Long projectId);
}
