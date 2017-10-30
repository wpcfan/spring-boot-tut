package dev.local.taskmgr.mongo.repositories;

import dev.local.taskmgr.mongo.domain.Profile;
import java.util.List;

public interface ProfileRepoCustom {
    List<Profile> findUsersInProject(String projectId);
}
