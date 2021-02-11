package com.hesoyam.pharmacy.medicine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public class MedicineReservationDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime pickUpDate;
    MedicineReservationItem medicineReservationItemId;
    List<MedicineReservationItem> medicineReservationItemList;
    MedicineReservationStatus medicineReservationStatus;
    Long medicineId;
    Long pharmacyId;
    String reservationCode;

    public MedicineReservationDTO(){
        //Empty ctor for JSON serializer
    }

    public MedicineReservationDTO(MedicineReservation medicineReservation) {
        this.pickUpDate = medicineReservation.getPickUpDate();
        this.medicineReservationItemList = medicineReservation.getMedicineReservationItems();
        this.medicineReservationStatus = medicineReservation.getMedicineReservationStatus();
        this.pharmacyId = medicineReservation.getPharmacy().getId();
        this.reservationCode = medicineReservation.getCode();
    }


    public MedicineReservationStatus getMedicineReservationStatus() {
        return medicineReservationStatus;
    }

    public void setMedicineReservationStatus(MedicineReservationStatus medicineReservationStatus) {
        this.medicineReservationStatus = medicineReservationStatus;
    }

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public List<MedicineReservationItem> getMedicineReservationItemList() {
        return medicineReservationItemList;
    }

    public void setMedicineReservationItemList(List<MedicineReservationItem> medicineReservationItemList) {
        this.medicineReservationItemList = medicineReservationItemList;
    }

    public MedicineReservationItem getMedicineReservationItemId() {
        return medicineReservationItemId;
    }

    public void setMedicineReservationItemId(MedicineReservationItem medicineReservationItemId) {
        this.medicineReservationItemId = medicineReservationItemId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
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
}
