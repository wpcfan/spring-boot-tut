package dev.local.taskmgr.controllers.wrappers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ProjectMemberIds {
    @Getter @Setter
    private List<Long> memberIds;
}
