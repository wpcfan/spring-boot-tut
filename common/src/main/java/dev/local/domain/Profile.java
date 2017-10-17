package dev.local.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.Set;

@Data
public class Profile {
    @Id
    private String id;
    // in fact, this is a username, not user's ObjectId
    // as this field is more used in common scenes
    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    private String username;
    private String name;
    private String avatar;
    private Address address;
    private CitizenId identity;
    private Date dateOfBirth;
    private Set<String> projectIdsJoined;
}
