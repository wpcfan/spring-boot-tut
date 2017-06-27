package dev.local.todo;

import dev.local.tasklist.TaskList;
import dev.local.user.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

/**
 * Todo是一个领域对象（domain object）
 */
@Data
public class Task {
    @Id private String id;
    private String desc;
    private boolean completed;
    @DBRef(lazy = true)
    private TaskList group;
    @DBRef(lazy = true)
    private User owner;
    @DBRef(lazy = true)
    private List<User> participants;
    private Date dueDate;
    private Date reminder;
    private int priority;
    private String remark;
}
