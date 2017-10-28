package dev.local.auth.services;

import dev.local.auth.dto.UserDTO;
import org.springframework.web.HttpRequestMethodNotSupportedException;

public interface AuthService {
    UserDTO register(UserDTO userDTO) throws HttpRequestMethodNotSupportedException;
}
