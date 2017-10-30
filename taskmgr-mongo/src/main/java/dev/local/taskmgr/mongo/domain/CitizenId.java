package dev.local.taskmgr.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class CitizenId implements Serializable{
    private static final long serialVersionUID = 1L;
    @Wither private int identityType;
    @Wither private String identityNo;
}
