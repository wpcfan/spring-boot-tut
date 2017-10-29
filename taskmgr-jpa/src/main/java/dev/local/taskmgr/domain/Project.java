package dev.local.taskmgr.domain;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Wither;

import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Value
@Entity
@Table(name = "taskmgr_project")
@ToString(exclude = {"owner", "members", "taskLists"})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private @Wither Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "prj_desc")
    private String desc;

    @Column(nullable = false)
    private String coverImg;

    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private boolean archived = false;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Profile owner;

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "taskmgr_profile_project",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")
    )
    private List<Profile> members = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<TaskList> taskLists = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Project
                && id != null
                && id.equals(((Project) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
