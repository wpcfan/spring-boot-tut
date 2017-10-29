package dev.local.taskmgr.domain;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Value
@Builder
@Entity
@Table(name = "taskmgr_task_list")
@ToString(exclude={"project"})
public class TaskList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Wither
    private Long id;

    private String name;

    @Wither
    @Column(name = "list_order")
    private int order;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @Wither
    private Project project;

    @OneToMany(mappedBy = "taskList")
    private List<Task> tasks;

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
