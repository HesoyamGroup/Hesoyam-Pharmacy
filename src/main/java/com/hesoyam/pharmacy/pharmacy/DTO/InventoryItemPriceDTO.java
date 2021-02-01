package com.hesoyam.pharmacy.pharmacy.DTO;

import com.hesoyam.pharmacy.pharmacy.model.InventoryItemPrice;
import com.hesoyam.pharmacy.util.DateTimeRange;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Min;

public class InventoryItemPriceDTO {

    private Long id;

    @Min(0)
    private double price;

    @NotNull
    private DateTimeRange range;

    public InventoryItemPriceDTO(){
        //Empty ctor for JSON serializer
    }

    public InventoryItemPriceDTO(InventoryItemPrice itemPrice){
        this.id = itemPrice.getId();
        this.price = itemPrice.getPrice();
        this.range = itemPrice.getValidThrough();
    }

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

    public DateTimeRange getRange() {
        return range;
    }

    public void setRange(DateTimeRange range) {
        this.range = range;
    }
}
