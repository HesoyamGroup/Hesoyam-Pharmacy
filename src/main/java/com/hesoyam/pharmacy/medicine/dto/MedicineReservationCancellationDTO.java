package com.hesoyam.pharmacy.medicine.dto;

public class MedicineReservationCancellationDTO {
    Long id;

    Long pharmacyId;

    String reservationCode;

    Long medicineId;

    public MedicineReservationCancellationDTO(Long id) {
        this.id = id;
    }

    public MedicineReservationCancellationDTO() {
        //Empty ctor for JSON serializer
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }
}
