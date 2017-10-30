package dev.local.taskmgr.mongo.dto;

import lombok.Getter;
import dev.local.taskmgr.mongo.domain.Profile;
import dev.local.taskmgr.mongo.domain.Project;
import java.util.List;

public class QueryProjectDTO extends Project {
    @Getter
    private List<Profile> members;
}
