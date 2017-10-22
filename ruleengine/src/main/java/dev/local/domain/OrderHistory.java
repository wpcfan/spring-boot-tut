package dev.local.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * OrderHistory 只可以添加，不能修改或删除，任何对于 OrderItem 的操作
 * 都会留痕。
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderHistory extends TimeStampedEntity{
    private String id;
    private OrderItem orderItem;
    private float amount;
    private OrderOperations operations;
}
