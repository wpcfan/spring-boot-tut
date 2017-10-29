package dev.local.taskmgr.repositories;

import dev.local.taskmgr.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Custom query example, count query is required for pagination to work
 *
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "select p from Project p inner join p.members m where m.username=?1 and p.enabled=?2 and p.archived=?3",
            countQuery = "select count(*) from Project p inner join p.members m where m.username=?1 and p.enabled=?2 and p.archived=?3")
    Page<Project> findRelated(String username, boolean enabled, boolean archived, Pageable pageable);
}
