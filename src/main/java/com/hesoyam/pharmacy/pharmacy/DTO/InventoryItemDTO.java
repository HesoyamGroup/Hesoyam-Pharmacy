package com.hesoyam.pharmacy.pharmacy.DTO;

import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class InventoryItemDTO {
    private Long id;
    private Long medicineId;
    private String medicineName;
    private int available;
    private int reserved;
    private List<InventoryItemPriceDTO> prices;

    public InventoryItemDTO(){
        //Empty ctor for JSON serializer
        this.prices = new ArrayList<>();
    }

    public InventoryItemDTO(InventoryItem inventoryItem){
        this.id = inventoryItem.getId();
        this.medicineId = inventoryItem.getMedicine().getId();
        this.medicineName = inventoryItem.getMedicine().getName();
        this.available = inventoryItem.getAvailable();
        this.reserved = inventoryItem.getReserved();
        this.prices = new ArrayList<>();
        inventoryItem.getPrices().forEach(itemPrice -> this.prices.add(new InventoryItemPriceDTO(itemPrice)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public List<InventoryItemPriceDTO> getPrices() {
        return prices;
    }

    public void setPrices(List<InventoryItemPriceDTO> prices) {
        this.prices = prices;
    }
}
