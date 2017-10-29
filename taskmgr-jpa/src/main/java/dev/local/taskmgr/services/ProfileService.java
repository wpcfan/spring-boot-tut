package dev.local.taskmgr.services;

import dev.local.taskmgr.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileService {
    Profile add(Profile profile, String userId);
    Profile update(Profile profile);
    Page<Profile> search(String filter, Pageable pageable);
    Profile findByUsername(String username);
}
