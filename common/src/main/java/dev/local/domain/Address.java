package dev.local.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;

@Data
@Builder
@AllArgsConstructor
public class Address {
    @Wither private String province;
    @Wither private String city;
    @Wither private String district;
    @Wither private String street;
}
