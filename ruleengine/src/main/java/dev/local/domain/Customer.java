package dev.local.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class Customer implements Serializable{
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String name;
    private final String username;
    private final Membership membership;
}
