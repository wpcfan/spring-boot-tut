package dev.local.services;

import dev.local.domain.User;
import dev.local.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User add(User user) {
        user.setRoles(Collections.singletonList("ROLE_USER"));
        return repository.insert(user);
    }

    @Override
    public String delete(String id) {
        repository.delete(id);
        return id;
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
}
