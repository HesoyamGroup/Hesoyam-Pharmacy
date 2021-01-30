package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class InventoryItemPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    @Min(0)
    protected double price;

    @Embedded
    protected DateTimeRange validThrough;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DateTimeRange getValidThrough() {
        return validThrough;
    }

    public void setValidThrough(DateTimeRange validThrough) {
        this.validThrough = validThrough;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;

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