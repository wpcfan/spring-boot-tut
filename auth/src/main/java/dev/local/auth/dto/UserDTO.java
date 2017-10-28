package dev.local.auth.dto;

import dev.local.auth.domain.User;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserDTO {
    @NonNull
    private String username;
    @NonNull
    private String password;

    public User build() {
        return new User()
                .withUsername(username)
                .withPassword(password);
    }
}
