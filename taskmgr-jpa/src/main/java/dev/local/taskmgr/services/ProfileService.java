package dev.local.services;

import dev.local.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {
    Profile add(Profile profile, String userId);
    Profile update(Profile profile);
    List<Profile> profilesByProject(String projectId);
    Page<Profile> search(String filter, Pageable pageable);
    Profile findByUsername(String username);
}
