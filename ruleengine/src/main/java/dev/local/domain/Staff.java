package dev.local.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Staff {
    private String id;
    private String name;
    private String username;
}
