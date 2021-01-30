package com.hesoyam.pharmacy.medicine.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicineReservationDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime pickUpDate;
    List<MedicineReservationItem> medicineReservationItemList;

    public MedicineReservationDTO() {
        medicineReservationItemList = new ArrayList<>();
    }

    public MedicineReservationDTO(LocalDateTime pickUpDate, List<MedicineReservationItem> medicineReservationItemList) {
        this.pickUpDate = pickUpDate;
        this.medicineReservationItemList = medicineReservationItemList;
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
