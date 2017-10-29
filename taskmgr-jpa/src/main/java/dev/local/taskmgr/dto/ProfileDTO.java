package dev.local.taskmgr.dto;

import dev.local.taskmgr.domain.Address;
import dev.local.taskmgr.domain.CitizenId;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProfileDTO {
    private String username;
    private String password;
    private String avatar;
    private String name;
    private Address address;
    private CitizenId identity;
    private Date dateOfBirth;
}
