package com.hesoyam.pharmacy.pharmacy.dto;

import com.hesoyam.pharmacy.medicine.dto.MedicineBasicInfoDTO;
import com.hesoyam.pharmacy.pharmacy.model.OrderItem;

public class OrderItemDTO {
    private Long id;
    private int quantity;
    private MedicineBasicInfoDTO medicine;

    public OrderItemDTO(){
        //Empty ctor for JSON serializer
    }

    public OrderItemDTO(OrderItem orderItem){
        this.id = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.medicine = new MedicineBasicInfoDTO(orderItem.getMedicine());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MedicineBasicInfoDTO getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicineBasicInfoDTO medicine) {
        this.medicine = medicine;
    }
}
