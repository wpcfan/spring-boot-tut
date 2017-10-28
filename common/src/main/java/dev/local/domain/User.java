package dev.local.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "prfo", )
public class User {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    @Column(nullable = false)
    private String username;
    @JsonIgnore @Getter
    private String password;
    @JsonIgnore
    private Date lastPasswordResetDate;
    @Builder.Default @Wither private Set<String> roles = new HashSet<>();

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
