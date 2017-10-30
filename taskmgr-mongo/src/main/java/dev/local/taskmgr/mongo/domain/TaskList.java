package dev.local.taskmgr.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
public class TaskList {
    @Id
    private String id;
    @Wither private String name;
    @Wither private int order;
    @Wither private String projectId;
}
