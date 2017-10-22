package dev.local.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.local.domain.Address;
import dev.local.domain.CitizenId;
import dev.local.domain.Profile;
import dev.local.domain.User;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserDTO {
    private String username;
    private String password;
    private String name;
    private String avatar;
    private CitizenId identity;
    private Address address;
    private Date dateOfBirth;

    public User buildUser() {
        return User.builder()
                .password(this.password)
                .username(this.username)
                .build();
    }

    public Profile buildProfile() {
        return Profile.builder()
                .name(this.name)
                .avatar(this.avatar)
                .dateOfBirth(this.dateOfBirth)
                .address(this.address)
                .identity(this.identity)
                .build();
    }
}
