package dev.local.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Membership {
    private int type;
    private String code;
    private float pointsRemained;
}
