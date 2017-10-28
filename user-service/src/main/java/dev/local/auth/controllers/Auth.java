package dev.local.auth.controllers;

import dev.local.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Auth {
    private String token;
    private Profile user;
}
