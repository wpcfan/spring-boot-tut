package dev.local.taskmgr.mongo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TaskHistory {
    private String taskId;
    private String operatorId;
    private String content;
    private Date createdAt;
}
