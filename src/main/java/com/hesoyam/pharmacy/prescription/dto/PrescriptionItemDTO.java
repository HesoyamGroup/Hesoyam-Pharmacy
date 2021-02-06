package com.hesoyam.pharmacy.prescription.dto;

import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;

public class PrescriptionItemDTO {
    private String medicine;
    private int quantity;

    public PrescriptionItemDTO(String medicine, int quantity) {
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public PrescriptionItemDTO() {
    }

    public PrescriptionItemDTO(PrescriptionItem prescriptionItem) {
        this.medicine = prescriptionItem.getMedicine().getName();
        this.quantity = prescriptionItem.getQuantity();
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
