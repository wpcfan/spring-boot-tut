package dev.local.taskmgr.domain;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Wither;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "taskmgr_task")
@Builder
@Value
@ToString(exclude={"taskList", "owner", "participants", "histories"})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Wither
    private Long id;

    @Column(name = "task_desc", nullable = false)
    private String desc;

    @Wither
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    @Wither
    private TaskList taskList;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Profile owner;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "taskmgr_profile_task",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Profile> participants;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Temporal(TemporalType.DATE)
    private Date reminder;

    private int priority;

    private String remark;

    @OneToMany(mappedBy="task")
    private List<TaskHistory> histories = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Task
                && id != null
                && id.equals(((Task) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
