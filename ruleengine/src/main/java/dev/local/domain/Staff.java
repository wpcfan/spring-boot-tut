package dev.local.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Staff implements Serializable {

    private final static long serialVersionUID = 1L;

    private String id;

    private String username;
    private String password;
}
