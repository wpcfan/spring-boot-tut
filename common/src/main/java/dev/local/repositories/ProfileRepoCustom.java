package dev.local.repositories;

import dev.local.domain.Profile;

import java.util.List;

public interface ProfileRepoCustom {
    List<Profile> findUsersInProject(String projectId);
}
