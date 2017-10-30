package dev.local.taskmgr.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * Todo是一个领域对象（domain object）
 */
@Data
@Builder
@AllArgsConstructor
public class Task {
    @Id private String id;
    @Wither private String desc;
    @Wither private boolean completed;
    @Wither private String taskListId;
    @Wither private String ownerId;
    @Wither private List<String> participantIds;
    @Wither private Date dueDate;
    @Wither private Date reminder;
    @Wither private int priority;
    @Wither private String remark;
    @Wither private List<TaskHistory> histories;
}
