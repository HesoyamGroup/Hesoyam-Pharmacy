package com.hesoyam.pharmacy.pharmacy.dto;

public class ShowOrderItemDTO {
    private Long id;
    private String medicineName;
    private Integer quantity;
    private Long medicineId;


    public ShowOrderItemDTO(){}

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
