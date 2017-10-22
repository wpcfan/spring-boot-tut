package dev.local.domain;

import lombok.Data;

import java.util.Date;

@Data
public class InventoryItem {
    private String id;
    private Product product;
    private String shipCode;
    private float amount;
    private Date purchaseDate;
    private Date expiryDate;
    private Date productionDate;
    private String location;
}
