package dev.local.taskmgr.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Wither private String province;
    @Wither private String city;
    @Wither private String district;
    @Wither private String street;
}
