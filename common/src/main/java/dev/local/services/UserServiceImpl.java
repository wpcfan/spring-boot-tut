package dev.local.services;

import dev.local.domain.User;
import dev.local.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User add(User user) {
        return repository.insert(
            user.withRoles(Collections.singleton("ROLE_USER"))
        );
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public User findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
