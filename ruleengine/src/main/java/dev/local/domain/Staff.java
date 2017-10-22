package dev.local.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
public class Staff {
    @Id
    private String id;

    private String username;
    private String password;
}
