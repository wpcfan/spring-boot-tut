package dev.local.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
