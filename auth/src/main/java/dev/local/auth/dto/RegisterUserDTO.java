package dev.local.auth.dto;

import dev.local.auth.domain.User;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class UserDTO {

    private String username;
    
    private String password;

    public User build() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
