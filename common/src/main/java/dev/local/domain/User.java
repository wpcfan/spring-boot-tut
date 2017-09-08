package dev.local.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
    private String password;
    private Date lastPasswordResetDate;
    @DBRef(lazy = true)
    private List<Role> roles;
    @DBRef(lazy = true)
    private List<Tenant> tenants;
}
