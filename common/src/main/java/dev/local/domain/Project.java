package dev.local.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class Project {
    @Id private String id;
    @NonNull
    private String name;
    private String desc;
    private boolean enabled = true;
    private boolean archived = false;
    private String ownerId;
    private Set<String> memberIds = new HashSet<>();
    private Set<String> taskListIds = new HashSet<>();
}
