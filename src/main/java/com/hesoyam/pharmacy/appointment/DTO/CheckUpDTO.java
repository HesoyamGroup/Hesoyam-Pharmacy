package com.hesoyam.pharmacy.appointment.DTO;

import java.time.LocalDateTime;

public class CheckUpDTO {
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private String pharmacyName;
    private LocalDateTime from;
    private LocalDateTime to;

    public CheckUpDTO(String patientFirstName, String patientLastName, String patientEmail, String pharmacyName,
                      LocalDateTime from, LocalDateTime to) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientEmail = patientEmail;
        this.pharmacyName = pharmacyName;
        this.from = from;
        this.to = to;
    }

    public CheckUpDTO() {
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

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
