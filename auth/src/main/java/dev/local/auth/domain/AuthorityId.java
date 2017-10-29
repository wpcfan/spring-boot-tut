package dev.local.auth.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityId implements Serializable {
    private static final long serialVersionUID = -8173857210615808268L;

    private String username;

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
