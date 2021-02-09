package com.hesoyam.pharmacy.prescription.dto;

import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;

public class PrescriptionItemDTO {
    private String medicine;
    private int quantity;
    private int therapyDuration;

    public PrescriptionItemDTO(String medicine, int quantity, int therapyDuration) {
        this.medicine = medicine;
        this.quantity = quantity;
        this.therapyDuration = therapyDuration;
    }

    public PrescriptionItemDTO() {
        //Empty ctor for JSON serializer
    }

    public PrescriptionItemDTO(PrescriptionItem prescriptionItem) {
        this.medicine = prescriptionItem.getMedicine().getName();
        this.quantity = prescriptionItem.getQuantity();
        this.therapyDuration = 0;
    }

    public int getTherapyDuration() {
        return therapyDuration;
    }

    public void setTherapyDuration(int therapyDuration) {
        this.therapyDuration = therapyDuration;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
