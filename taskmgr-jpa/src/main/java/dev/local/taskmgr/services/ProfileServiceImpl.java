package dev.local.taskmgr.services;

import dev.local.taskmgr.domain.Profile;
import dev.local.taskmgr.repositories.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repository;

    @Override
    public Profile add(Profile profile, String userId) {
        return repository.save(profile);
    }

    @Override
    public Profile update(Profile profile) {
        return repository.save(profile);
    }

    @Override
    public Page<Profile> search(String filter, Pageable pageable) {
        return repository.findDistinctProfileByUsernameLikeOrNameLike(filter, filter, pageable);
    }

    @Override
    public Profile findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
