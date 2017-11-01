package dev.local.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@Entity
@Table(name = "users")
@ToString(exclude = {"authorities"})
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String username;

    @JsonIgnore
    private String password;

    @Builder.Default
    private boolean enabled = true;

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
