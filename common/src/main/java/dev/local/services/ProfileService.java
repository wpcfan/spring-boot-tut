package dev.local.services;

import dev.local.domain.Profile;
import org.springframework.data.domain.Page;

public interface ProfileService {
    Profile add(Profile profile, String userId);
    Profile update(Profile profile);
    Page<Profile> profilesByProject(String projectId);
}
