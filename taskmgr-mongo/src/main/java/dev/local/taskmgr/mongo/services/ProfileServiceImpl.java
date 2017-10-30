package dev.local.taskmgr.mongo.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import dev.local.taskmgr.mongo.domain.Profile;
import dev.local.taskmgr.mongo.repositories.ProfileRepoCustom;
import dev.local.taskmgr.mongo.repositories.ProfileRepository;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repository;
    private final ProfileRepoCustom repoCustom;

    @Override
    public Profile add(Profile profile, String userId) {
        profile.setUsername(userId);
        return repository.insert(profile);
    }

    @Override
    public Profile update(Profile profile) {
        return repository.save(profile);
    }

    @Override
    public List<Profile> profilesByProject(final String projectId) {
        return repoCustom.findUsersInProject(projectId);
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
