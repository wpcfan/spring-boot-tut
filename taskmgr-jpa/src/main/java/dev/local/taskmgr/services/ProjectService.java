package dev.local.taskmgr.services;

import dev.local.taskmgr.domain.Project;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by wangpeng on 2017/4/18.
 */
public interface ProjectService {
    Project add(Project project, String username);
    void delete(Long id);
    Page<Project> findRelated(String username, Pageable pageable);
    Project findById(Long id);
    Project update(Project project);
    Project inviteMembers(Long id, List<Long> memberIds);
}
