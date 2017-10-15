package dev.local.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TaskList {
    @Id
    private String id;
    private String name;
    private int order;
    private String projectId;
}
