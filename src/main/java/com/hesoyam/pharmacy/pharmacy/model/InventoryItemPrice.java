package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryItemPrice)) return false;
        InventoryItemPrice itemPrice = (InventoryItemPrice) o;
        return Objects.equals(getId(), itemPrice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public boolean isConflictingWith(InventoryItemPrice newInventoryItemPrice) {
        return this.validThrough.overlaps(newInventoryItemPrice.getValidThrough());
    }
}