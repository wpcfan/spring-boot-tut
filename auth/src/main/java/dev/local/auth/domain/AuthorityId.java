package dev.local.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityId implements Serializable {
    private static final long serialVersionUID = -8173857210615808268L;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String authority;
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof AuthorityId
                && username != null && authority != null
                && username.equals(((AuthorityId) o).username)
                && authority.equals(((AuthorityId) o).authority);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
