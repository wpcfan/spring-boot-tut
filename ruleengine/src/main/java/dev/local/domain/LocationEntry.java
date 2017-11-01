package dev.local.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;


@EqualsAndHashCode(callSuper = true)
@Value
public class LocationEntry extends TimeStampedEntity{
    private final static long serialVersionUID = 1L;
    private Long id;
    private Location location;
    private Product product;
    private String batchNumber;
    private float quantity;
    private float value;
}
