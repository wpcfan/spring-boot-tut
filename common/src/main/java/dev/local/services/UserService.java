package dev.local.services;

import dev.local.domain.User;

public interface UserService {
    User add(User user);
    String delete(String id);
    User update(User user);
    User findById(String id);
    User findByUsername(String username);
}
