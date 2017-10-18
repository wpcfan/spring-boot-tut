package dev.local.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class TaskHistory {
    private String taskId;
    private String operatorId;
    private String content;
    private Date createdAt;
}
