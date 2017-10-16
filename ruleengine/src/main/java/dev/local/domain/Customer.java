package dev.local.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private String id;
    private Membership membership;
    private String name;
    private String username;
}
