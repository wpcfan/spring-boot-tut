package dev.local.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Data
public class Project {
    @Id private String id;
    private String name;
    private String desc;
    private String coverImg;
    private boolean enabled = true;
    private boolean archived = false;
    private String ownerId;
    private Set<String> memberIds = new HashSet<>();
    private Set<String> taskListIds = new HashSet<>();
}
