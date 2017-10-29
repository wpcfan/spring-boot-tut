package dev.local.taskmgr.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Wither;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "task_history")
@Builder
@AllArgsConstructor
@ToString(exclude = "task")
public class TaskHistory extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @ManyToOne(targetEntity = Task.class)
    @Wither
    private Task task;

    @Wither
    private String content;
}
