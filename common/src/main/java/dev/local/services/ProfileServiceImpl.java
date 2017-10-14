package dev.local.services;

import dev.local.domain.Profile;
import dev.local.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Profile add(Profile profile, String userId) {
        profile.setUserId(userId);
        return repository.insert(profile);
    }

    @Override
    public Profile update(Profile profile) {
        return repository.save(profile);
    }
}
