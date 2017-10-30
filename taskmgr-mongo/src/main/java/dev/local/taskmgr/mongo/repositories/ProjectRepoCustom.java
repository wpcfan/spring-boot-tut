package dev.local.taskmgr.mongo.repositories;

import dev.local.taskmgr.mongo.dto.QueryProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectRepoCustom {
    Page<QueryProjectDTO> getJoinedProjectsWithUsers(String username, Pageable pageable);
}
