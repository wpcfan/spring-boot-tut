package dev.local.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.local.domain.Address;
import dev.local.domain.CitizenId;
import dev.local.domain.Profile;
import dev.local.domain.User;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private String username;
    private String password;
    private String name;
    private String avatar;
    private CitizenId identity;
    private Address address;
    private Date dateOfBirth;

    public User buildUser() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }

    public Profile buildProfile() {
        Profile profile = new Profile();
        profile.setName(this.name);
        profile.setAvatar(this.avatar);
        profile.setDateOfBirth(this.dateOfBirth);
        profile.setAddress(this.address);
        profile.setIdentity(this.identity);
        return profile;
    }
}
