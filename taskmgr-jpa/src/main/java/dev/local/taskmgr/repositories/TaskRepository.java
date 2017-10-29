package dev.local.taskmgr.repositories;

import dev.local.taskmgr.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByParticipantsContaining(String username);
    Page<Task> findByTaskListId(Long taskListId, Pageable pageable);
    List<Task> findByTaskListId(Long taskListId);
}
