package dev.local.taskmgr.domain;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Value
@Builder
@Embeddable
public class CitizenId implements Serializable{
    private static final long serialVersionUID = 1L;
    @Column(name = "id_type")
    @Wither private int identityType;
    @Column(name = "id_no")
    @Wither private String identityNo;
}
