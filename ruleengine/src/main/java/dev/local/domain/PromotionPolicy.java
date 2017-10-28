package dev.local.domain;

import lombok.Data;

@Data
public class PromotionPolicy {
    private String id;
    private int type; //整个订单还是单个产品
    private float discount;
    private float points;
    private Product bonus;
    private boolean immutable;
}
