package dev.local.api.controllers.wrappers;

import lombok.Data;

@Data
public class SwapList {
    private String srcListId;
    private String targetListId;
}
