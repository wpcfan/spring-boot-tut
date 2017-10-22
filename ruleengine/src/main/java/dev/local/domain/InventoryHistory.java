package dev.local.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class InventoryHistory extends TimeStampedEntity{
    private String id;
    private InventoryItem item;
    private OrderItem orderItem;
    private float amount;
    private InventoryOperations operations;
    private Staff requestedBy;
    private Staff auditedBy;
    private Date auditedDate;
    private float purchasePrice;
}
