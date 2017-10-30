package dev.local.taskmgr.mongo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import dev.local.taskmgr.mongo.domain.Profile;
import java.util.List;

public interface ProfileService {
    Profile add(Profile profile, String userId);
    Profile update(Profile profile);
    List<Profile> profilesByProject(String projectId);
    Page<Profile> search(String filter, Pageable pageable);
    Profile findByUsername(String username);
}
