package dev.local.services;

import dev.local.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User add(User user);
    String delete(String id);
    User update(User user);
    User findById(String id);
    User findByUsername(String username);
}
