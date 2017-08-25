package dev.local.auth;

import dev.local.domain.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
