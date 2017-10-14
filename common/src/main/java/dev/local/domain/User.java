package dev.local.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.*;

@Data

public class User {
    @Id
    private String id;
    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    private String username;
    @JsonIgnore @Getter
    private String password;
    @JsonIgnore
    private Date lastPasswordResetDate;
    private Set<String> roles = new HashSet<>();
    private Set<String> joinedProjectIds = new HashSet<>();

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
