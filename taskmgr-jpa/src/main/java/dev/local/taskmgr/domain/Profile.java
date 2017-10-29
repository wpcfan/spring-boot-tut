package dev.local.taskmgr.domain;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "profile",
        indexes = {
            @Index(name = "idx_profile_username", columnList = "username", unique = true)
        }
)
@Value
@Builder
@ToString(exclude = {"projectsJoined", "tasksJoined"})
public class Profile implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String name;

    private String avatar;

    private Address address;

    private CitizenId identity;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @ManyToMany(mappedBy = "members")
    private List<Project> projectsJoined;

    @ManyToMany(mappedBy = "participants")
    private List<Task> tasksJoined;

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
