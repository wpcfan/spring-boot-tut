package dev.local.taskmgr.mongo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import dev.local.taskmgr.mongo.domain.Project;
import dev.local.taskmgr.mongo.dto.QueryProjectDTO;
import java.util.List;

/**
 * Created by wangpeng on 2017/4/18.
 */
public interface ProjectService {
    Project add(Project project, String username);
    void delete(String id);
    Page<QueryProjectDTO> findRelated(String userId, Pageable pageable);
    Project findById(String id);
    Project update(Project project);
    Project inviteMembers(String id, List<String> memberIds);
}
