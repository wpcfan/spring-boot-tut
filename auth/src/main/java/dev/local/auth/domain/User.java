package dev.local.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Wither;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Wither @Getter @Setter
    private String username;

    @JsonIgnore
    @Wither @Getter @Setter
    private String password;

    @Wither @Getter @Setter
    private boolean enabled = true;

    @Wither @Getter @Setter
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof User
                && username != null
                && username.equals(((User) o).username);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public UserDetails toUserDetails() {
        return new CustomUserDetails(username, password, enabled, mapToGrantedAuthorities(authorities));
    }

    private static Set<GrantedAuthority> mapToGrantedAuthorities(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getId().getAuthority()))
                .collect(Collectors.toSet());
    }
}
