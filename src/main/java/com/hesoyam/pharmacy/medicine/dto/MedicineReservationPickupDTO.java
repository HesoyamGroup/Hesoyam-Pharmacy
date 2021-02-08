package com.hesoyam.pharmacy.medicine.dto;

import com.hesoyam.pharmacy.medicine.model.MedicineReservation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MedicineReservationPickupDTO {
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private LocalDateTime pickUpDate;
    private String code;
    private String medicineReservationStatus;
    private boolean valid;

    public MedicineReservationPickupDTO(MedicineReservation reservation){
        this.patientEmail = reservation.getPatient().getEmail();
        this.patientFirstName = reservation.getPatient().getFirstName();
        this.patientLastName = reservation.getPatient().getLastName();
        this.pickUpDate = reservation.getPickUpDate();
        this.code = reservation.getCode();
        this.medicineReservationStatus = reservation.getMedicineReservationStatus().toString();
        checkValidity();
    }

    public MedicineReservationPickupDTO(){
        //Empty ctor for JSON serializer
    }

    private void checkValidity() {
        if(this.pickUpDate.isBefore(LocalDateTime.now()) && this.pickUpDate.isAfter(LocalDateTime.now().minus(
                24, ChronoUnit.HOURS)))
            this.valid = false;
        else
            this.valid = true;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMedicineReservationStatus() {
        return medicineReservationStatus;
    }

    public void setMedicineReservationStatus(String medicineReservationStatus) {
        this.medicineReservationStatus = medicineReservationStatus;
    }
}
