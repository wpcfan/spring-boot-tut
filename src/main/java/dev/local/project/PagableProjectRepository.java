package dev.local.project;

import dev.local.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 项目信息存储
 */
@Repository
public interface PagableProjectRepository extends PagingAndSortingRepository<Project, String> {
    Page<Project> findByMembersContainingAndEnabledAndArchived(User user, boolean enabled, boolean archived, Pageable pageable);
}
