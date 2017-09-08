package dev.local.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Privilege {
    @Id
    private String id;
    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    @NonNull
    private String name;
    @DBRef(lazy = true)
    private List<Role> roles;
}
