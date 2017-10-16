package dev.local.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderHistory {
    private String id;
    private OrderItem orderItem;
    private Date createdAt;
}
