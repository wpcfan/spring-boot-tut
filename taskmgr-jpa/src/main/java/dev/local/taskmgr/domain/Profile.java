package dev.local.taskmgr.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "profile",
        indexes = {
            @Index(name = "idx_profile_username", columnList = "username", unique = true)
        }
)
public class Profile implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // in fact, this is a username, not user's ObjectId
    // as this field is more used in common scenes
    private String username;

    @Wither
    private String name;

    @Wither
    private String avatar;

    @Wither
    private Address address;

    @Wither
    private CitizenId identity;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @Wither private Date dateOfBirth;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "profile_project",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id")
    )
    @Wither
    private Set<String> projectsJoined;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "profile_task",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id")
    )
    @Wither
    private Set<String> tasksJoined;

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Profile
                && id != null
                && id.equals(((Profile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
