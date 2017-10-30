package dev.local.taskmgr.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id private String id;
    @Wither private String name;
    @Wither private String desc;
    @Wither private String coverImg;
    @Builder.Default @Wither private boolean enabled = true;
    @Builder.Default @Wither private boolean archived = false;
    @Wither private String ownerId;
    @Builder.Default @Wither private Set<String> memberIds = new HashSet<>();
    @Builder.Default @Wither private Set<String> taskListIds = new HashSet<>();
}
