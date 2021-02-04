package com.hesoyam.pharmacy.storage.dto;

import com.hesoyam.pharmacy.medicine.model.Medicine;

public class AddStorageItemDTO {
    private Medicine medicine;

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
