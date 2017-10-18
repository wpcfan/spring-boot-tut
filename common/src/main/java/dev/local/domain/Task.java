package dev.local.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

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
    private String taskListId;
    private String ownerId;
    private List<String> participantIds;
    private Date dueDate;
    private Date reminder;
    private int priority;
    private String remark;
    private List<TaskHistory> histories;
}
