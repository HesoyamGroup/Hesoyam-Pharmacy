package com.hesoyam.pharmacy.medicine.dto;

public class MedicineReservationCancellationDTO {
    Long id;

    public MedicineReservationCancellationDTO(Long id) {
        this.id = id;
    }

    public MedicineReservationCancellationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
