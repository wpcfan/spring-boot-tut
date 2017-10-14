package dev.local.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<String> roles = new ArrayList<>();
    private List<String> joinedProjectIds = new ArrayList<>();

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
