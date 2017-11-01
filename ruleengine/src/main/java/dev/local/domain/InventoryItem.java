package dev.local.domain;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

@Value
@Builder
public class InventoryItem implements Serializable {
    private final static long serialVersionUID = 1L;
    private String id;
    private Product product;
    private String shipCode;
    private float amount;
    private Date purchaseDate;
    private Date expiryDate;
    private Date productionDate;
    private String location;
}
