package dev.local.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Embeddable
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Wither private String province;
    @Wither private String city;
    @Wither private String district;
    @Wither private String street;
}
