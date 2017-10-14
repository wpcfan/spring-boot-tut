package dev.local.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Profile {
    @Id
    private String id;
    private String userId;
    private String name;
    private String avatar;
    private Address address;
    private CitizenId identity;
    private Date dateOfBirth;
}
