package dev.local.taskmgr.domain;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Value
@Builder
@Embeddable
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Wither private String province;
    @Wither private String city;
    @Wither private String district;
    @Wither private String street;
}
