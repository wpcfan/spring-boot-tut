package dev.local.auth.services;

import dev.local.auth.dto.RegisterUserDTO;
import org.springframework.web.HttpRequestMethodNotSupportedException;

public interface AuthService {
    RegisterUserDTO register(RegisterUserDTO userDTO) throws HttpRequestMethodNotSupportedException;
}
