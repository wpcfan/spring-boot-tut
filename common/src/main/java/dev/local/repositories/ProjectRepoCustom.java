package dev.local.repositories;

import dev.local.dto.QueryProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectRepoCustom {
    Page<QueryProjectDTO> getJoinedProjectsWithUsers(String username, Pageable pageable);
}
