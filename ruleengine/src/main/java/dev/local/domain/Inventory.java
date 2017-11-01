package dev.local.domain;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
@Builder
public class Inventory implements Serializable {
    private final static long serialVersionUID = 1L;
    private List<InventoryItem> inventoryItems;
}
