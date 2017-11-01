package dev.local.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderItem implements Serializable {
    private final static long serialVersionUID = 1L;
    private String id;
    private Product product;
    private float amount;
    private float salePrice;
    private Staff soldBy;
    private int status;
}
