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
    List<MedicineReservationItem> medicineReservationItemList;
    MedicineReservationStatus medicineReservationStatus;

    public MedicineReservationDTO(){
    }

    public MedicineReservationDTO(MedicineReservation medicineReservation) {
        this.pickUpDate = medicineReservation.getPickUpDate();
        this.medicineReservationItemList = medicineReservation.getMedicineReservationItems();
        this.medicineReservationStatus = medicineReservation.getMedicineReservationStatus();
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
}
