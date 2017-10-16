package dev.local.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private String id;
    private List<OrderItem> orderItems;
    private Customer buyer;
    private int Status;
    private Staff requestedBy;
    private Staff auditBy;
    private Date closeDate;
}
