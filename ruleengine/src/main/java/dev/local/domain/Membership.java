package dev.local.domain;

import java.io.Serializable;

import dev.local.domain.enumerations.MembershipType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Membership implements Serializable {
    private final static long serialVersionUID = 1L;
    private MembershipType type;
    private String code;
    private float pointsRemained;
}
