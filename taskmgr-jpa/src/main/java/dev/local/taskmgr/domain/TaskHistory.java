package dev.local.taskmgr.domain;

import lombok.Builder;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "taskmgr_task_history")
@Builder
@ToString(exclude = "task")
public class TaskHistory extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Task.class)
    private Task task;

    private String content;

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof TaskHistory
                && id != null
                && id.equals(((TaskHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
