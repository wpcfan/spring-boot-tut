package dev.local.domain;

import dev.local.domain.enumerations.LocationType;
import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Location implements Serializable {
    private final static long serialVersionUID = 1L;
    private Long id;
    private String name;
    private LocationType type;
    private List<LocationEntry> entries;
}
