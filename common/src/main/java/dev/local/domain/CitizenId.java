package dev.local.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Embeddable
public class CitizenId implements Serializable{
    private static final long serialVersionUID = 1L;
    @Column(name = "id_type")
    @Wither private int identityType;
    @Column(name = "id_no")
    @Wither private String identityNo;
}
