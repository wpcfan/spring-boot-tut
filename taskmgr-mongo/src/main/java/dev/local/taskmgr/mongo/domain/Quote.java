package dev.local.taskmgr.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
    @Wither private String cn;
    @Wither private String en;
    @Wither private String pic;
}
