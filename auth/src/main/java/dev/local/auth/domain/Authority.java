package dev.local.auth.domain;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "authorities")
@ToString(exclude = {"user"})
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private AuthorityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "username", referencedColumnName = "username")
    @MapsId("username")
    private User user;

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Authority
                && id != null
                && id.equals(((Authority) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
