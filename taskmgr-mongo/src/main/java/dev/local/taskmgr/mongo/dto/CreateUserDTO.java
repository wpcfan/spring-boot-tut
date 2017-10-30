package dev.local.taskmgr.mongo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.local.taskmgr.mongo.domain.Address;
import dev.local.taskmgr.mongo.domain.CitizenId;
import dev.local.taskmgr.mongo.domain.Profile;
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
