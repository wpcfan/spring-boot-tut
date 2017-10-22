package dev.local.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;

@Data
@Builder
@AllArgsConstructor
public class CitizenId {
    @Wither private int identityType;
    @Wither private String identityNo;
}
