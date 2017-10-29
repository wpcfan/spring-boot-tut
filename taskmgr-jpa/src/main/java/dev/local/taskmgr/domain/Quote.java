package dev.local.taskmgr.domain;

import lombok.*;
import lombok.experimental.Wither;

import java.io.Serializable;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quote implements Serializable{
    @Wither private String cn;
    @Wither private String en;
    @Wither private String pic;
}
