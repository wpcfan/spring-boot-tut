package dev.local.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Data
public class LocationEntry extends TimeStampedEntity{
    @Id
    private Long id;
    private Location location;
    private Product product;
    private String batchNumber;
    private float quantity;
    private float value;
}
