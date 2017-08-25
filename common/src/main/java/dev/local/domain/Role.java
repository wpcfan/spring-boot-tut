package dev.local.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Set;

@Data
public class Role {
    @Id
    private String id;

    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    private String name;

    private Set<Privilege> privileges;
}
