package dev.local.taskmgr.domain;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class Quote implements Serializable{
    private static final long serialVersionUID = 1L;

    private String cn;

    private String en;

    private String pic;
}
