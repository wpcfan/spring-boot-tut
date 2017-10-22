package dev.local.services;

import dev.local.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User add(User user);
    void delete(String id);
    User update(User user);
    User findById(String id);
    User findByUsername(String username);
    Page<User> findAll(Pageable pageable);
}
