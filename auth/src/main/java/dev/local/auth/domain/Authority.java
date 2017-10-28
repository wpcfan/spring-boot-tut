package dev.local.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Wither;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Wither @Getter @Setter
    private AuthorityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "username", referencedColumnName = "username")
    @MapsId("username")
    @Wither @Getter @Setter
    private User user;

}
