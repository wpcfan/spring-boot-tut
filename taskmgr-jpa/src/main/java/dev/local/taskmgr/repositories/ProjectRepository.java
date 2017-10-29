package dev.local.taskmgr.repositories;

import dev.local.taskmgr.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wangpeng on 2017/4/19.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("select p from Project p inner join p.members m where m.username=:username and p.enabled=:enabled and p.archived=:archived")
    Page<Project> findRelated(String username, boolean enabled, boolean archived, Pageable pageable);
}
