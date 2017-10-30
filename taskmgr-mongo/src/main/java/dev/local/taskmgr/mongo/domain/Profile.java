package dev.local.taskmgr.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class Profile {
    @Id
    private String id;
    // in fact, this is a username, not user's ObjectId
    // as this field is more used in common scenes
    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    private String username;
    @Wither private String name;
    @Wither private String avatar;
    @Wither private Address address;
    @Wither private CitizenId identity;
    @Wither private Date dateOfBirth;
    @Wither private Set<String> projectIdsJoined;
}
