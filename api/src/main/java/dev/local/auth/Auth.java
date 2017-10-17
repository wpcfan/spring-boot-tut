package dev.local.auth;

import dev.local.domain.Profile;
import dev.local.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Auth {
    private String token;
    private Profile user;
}
