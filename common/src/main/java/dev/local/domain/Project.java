package dev.local.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@NoArgsConstructor
public class Project {
    @Id private String id;
    @NonNull
    private String name;
    private String desc;
    private boolean enabled = true;
    private boolean archived = false;
    @DBRef(lazy = true)
    private User owner;
    @DBRef(lazy = true)
    private List<User> members;
    @DBRef(lazy = true)
    private List<TaskList> groups;
}
