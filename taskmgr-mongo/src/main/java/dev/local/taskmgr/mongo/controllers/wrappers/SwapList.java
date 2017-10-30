package dev.local.taskmgr.mongo.controllers.wrappers;

import lombok.Data;

@Data
public class SwapList {
    private String srcListId;
    private String targetListId;
}
