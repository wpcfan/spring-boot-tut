package dev.local.secruity;

import java.util.List;
import java.util.stream.Collectors;


import dev.local.domain.Role;
import dev.local.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
        return authorities.stream()
                .map(a -> new SimpleGrantedAuthority(a.getName()))
                .collect(Collectors.toList());
    }
}

