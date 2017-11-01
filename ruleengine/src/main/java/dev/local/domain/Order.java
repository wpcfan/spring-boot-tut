package dev.local.domain;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Value
@Builder
public class Order implements Serializable {
    private final static long serialVersionUID = 1L;
    private String id;
    private List<OrderItem> orderItems;
    private Customer buyer;
    private int Status;
    private Staff requestedBy;
    private Staff auditBy;
    /**
     * 收银员
     */
    private Staff cashier;
    private Date closeDate;
}
