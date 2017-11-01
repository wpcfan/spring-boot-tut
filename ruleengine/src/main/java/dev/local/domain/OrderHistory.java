package dev.local.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import dev.local.domain.enumerations.OrderOperations;

/**
 * OrderHistory 只可以添加，不能修改或删除，任何对于 OrderItem 的操作
 * 都会留痕。
 */
@EqualsAndHashCode(callSuper = true)
@Value
@Builder
public class OrderHistory extends TimeStampedEntity{
    private final static long serialVersionUID = 1L;
    private String id;
    private OrderItem orderItem;
    private float amount;
    private OrderOperations operations;
}
