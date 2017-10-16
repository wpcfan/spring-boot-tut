package dev.local.domain;

import lombok.Data;

import java.util.Date;

@Data
public class InventoryHistory {
    private String id;
    private InventoryItem item;
    private float amount;
    private Date createdDate;
    private Staff requestedBy;
    private Staff auditedBy;
    private float purchasePrice;
}
