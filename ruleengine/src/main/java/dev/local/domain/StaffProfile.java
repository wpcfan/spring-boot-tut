package dev.local.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StaffProfile implements Serializable {
    private final static long serialVersionUID = 1L;

    private String id;
    private Staff staff;
    private String name;
    private boolean gender;

}
