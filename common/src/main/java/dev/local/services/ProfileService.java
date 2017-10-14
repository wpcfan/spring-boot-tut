package dev.local.services;

import dev.local.domain.Profile;

public interface ProfileService {
    Profile add(Profile profile, String userId);
    Profile update(Profile profile);
}
