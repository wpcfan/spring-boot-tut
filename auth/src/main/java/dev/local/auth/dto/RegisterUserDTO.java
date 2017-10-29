package dev.local.auth.dto;

import dev.local.auth.domain.User;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegisterUserDTO {

    private String username;

    private String password;

    public User build() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
