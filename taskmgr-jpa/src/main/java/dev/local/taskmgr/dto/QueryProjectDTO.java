package dev.local.taskmgr.dto;

import lombok.Getter;
import dev.local.taskmgr.domain.Profile;
import java.util.List;

public class QueryProjectDTO {
    @Getter
    private List<Profile> members;
}
