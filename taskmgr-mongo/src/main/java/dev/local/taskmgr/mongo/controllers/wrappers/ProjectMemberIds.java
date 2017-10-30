package dev.local.taskmgr.mongo.controllers.wrappers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ProjectMemberIds {
    @Getter @Setter
    private List<String> memberIds;
}
