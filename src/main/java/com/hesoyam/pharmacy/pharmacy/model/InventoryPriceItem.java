package com.hesoyam.pharmacy.pharmacy.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InventoryPriceItem extends PriceItem {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;

    public InventoryPriceItem() {}


    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem newInventoryItem) {
        if (this.inventoryItem == null || !this.inventoryItem.equals(newInventoryItem)) {
            if (this.inventoryItem != null) {
                InventoryItem oldInventoryItem = this.inventoryItem;
                this.inventoryItem = null;
                oldInventoryItem.removePrices(this);
            }
            if (newInventoryItem != null) {
                this.inventoryItem = newInventoryItem;
                this.inventoryItem.addPrices(this);
            }
        }
    }

}