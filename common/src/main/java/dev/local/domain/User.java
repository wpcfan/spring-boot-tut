package dev.local.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class User {
    @Id
    private String id;
    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    @NonNull
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private Date lastPasswordResetDate;
    @JsonIgnore
    @DBRef
    private List<Role> roles;
}
