package dev.local.taskmgr.domain;

import lombok.*;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
@ToString(exclude = {"owner", "members", "taskLists"})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Wither
    private String name;

    @Wither
    private String desc;

    @Wither
    private String coverImg;

    @Builder.Default @Wither
    private boolean enabled = true;

    @Builder.Default @Wither
    private boolean archived = false;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @Getter @Setter @Wither
    private Profile owner;

    @Builder.Default @Wither
    @ManyToMany(mappedBy = "projectsJoined")
    private Set<String> members = new HashSet<>();

    @Builder.Default @Wither
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<String> taskLists = new ArrayList<>();

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
