package dev.local.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class TaskList {
    @Id
    private String id;
    private String name;
    private int order;
    @DBRef(lazy = true)
    private Project project;
}
