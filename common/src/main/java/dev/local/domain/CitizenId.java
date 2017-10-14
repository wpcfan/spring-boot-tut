package dev.local.domain;

import lombok.Data;

@Data
public class CitizenId {
    private int identityType;
    private String identityNo;
}
