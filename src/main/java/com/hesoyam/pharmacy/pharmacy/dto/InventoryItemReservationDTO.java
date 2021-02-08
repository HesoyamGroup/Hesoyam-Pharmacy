package com.hesoyam.pharmacy.pharmacy.dto;

import javax.validation.constraints.NotNull;

public class InventoryItemReservationDTO {

    @NotNull(message = "Pharmacy ID must be provided")
    private Long pharmacyId;

    @NotNull(message = "Medicine ID must be provided")
    private Long medicineId;

    public InventoryItemReservationDTO() {
        //Empty ctor for JSON serializer
    }

    public InventoryItemReservationDTO(@NotNull(message = "Pharmacy ID must be provided") Long pharmacyId, @NotNull(message = "Medicine ID must be provided") Long medicineId) {
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }
}
