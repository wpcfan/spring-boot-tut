package dev.local.services;

import dev.local.domain.Profile;
import dev.local.domain.Project;
import dev.local.repositories.ProfileRepository;
import dev.local.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProfileServiceImpl(
            ProfileRepository repository,
            ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

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
    public Page<Profile> profilesByProject(final String projectId) {
        Project project = projectRepository.findOne(projectId);

        return null;
    }
}
