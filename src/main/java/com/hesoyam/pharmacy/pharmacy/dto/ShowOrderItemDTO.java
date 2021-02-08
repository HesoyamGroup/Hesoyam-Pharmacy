package com.hesoyam.pharmacy.pharmacy.dto;

import com.hesoyam.pharmacy.pharmacy.model.OrderItem;

public class ShowOrderItemDTO {
    private Long id;
    private String medicineName;
    private Integer quantity;
    private Long medicineId;

    public ShowOrderItemDTO(){
        //Empty ctor for JSON serializer
    }

    public ShowOrderItemDTO(OrderItem orderItem){
        this.id = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.medicineId = orderItem.getMedicine().getId();
        this.medicineName = orderItem.getMedicine().getName();
    }

    public ShowOrderItemDTO(Long id, String medicineName, Long medicineId, Integer quantity) {
        this.id = id;
        this.medicineName = medicineName;
        this.medicineId = medicineId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
