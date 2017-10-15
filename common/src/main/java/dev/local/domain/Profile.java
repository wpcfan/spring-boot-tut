package dev.local.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Profile {
    @Id
    private String id;
    // in fact, this is a username, not user's ObjectId
    // as this field is more used in common scenes
    private String username;
    private String name;
    private String avatar;
    private Address address;
    private CitizenId identity;
    private Date dateOfBirth;
}
