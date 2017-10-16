package dev.local.domain;

import lombok.Data;

import java.util.List;

@Data
public class Inventory {
    private List<InventoryItem> inventoryItems;
}
