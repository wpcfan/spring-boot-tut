package dev.local.taskmgr.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
@ToString(exclude={"project"})
public class TaskList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Wither
    private String name;

    @Wither
    private int order;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @Wither
    private Project project;

    @OneToMany(mappedBy = "taskList")
    @Wither
    private List tasks;

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof TaskList
                && id != null
                && id.equals(((TaskList) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
