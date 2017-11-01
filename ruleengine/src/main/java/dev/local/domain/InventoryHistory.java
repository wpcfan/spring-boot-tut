package dev.local.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import dev.local.domain.enumerations.InventoryOperations;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder
public class InventoryHistory extends TimeStampedEntity {

    private final static long serialVersionUID = 1L;

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
