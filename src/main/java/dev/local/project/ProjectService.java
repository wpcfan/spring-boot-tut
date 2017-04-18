package dev.local.project;

import java.util.Set;

/**
 * Created by wangpeng on 2017/4/18.
 */
public interface ProjectService {
    Project add(Project project, String userId);
    Project delete(String id);
    Set<Project> findRelated(String userId);
    Project findById(String id);
    Project update(Project project);
}
