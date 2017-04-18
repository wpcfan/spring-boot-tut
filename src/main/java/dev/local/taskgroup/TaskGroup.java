package dev.local.taskgroup;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TaskGroup {
    @Id
    private String id;
    private String name;
    private int order;
}
