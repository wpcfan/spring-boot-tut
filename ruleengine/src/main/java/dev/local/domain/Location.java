package dev.local.domain;

import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
public class Location {
    @Id
    private Long id;
    private String name;
    private LocationType type;
    private List<LocationEntry> entries;
}
