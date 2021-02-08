package com.hesoyam.pharmacy.appointment.dto;

import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.CheckUp;

import java.time.LocalDateTime;

public class CheckUpDTO {
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private String pharmacyName;
    private LocalDateTime from;
    private LocalDateTime to;
    private String appointmentStatus;
    private long pharmacyId;

    public CheckUpDTO(String patientFirstName, String patientLastName, String patientEmail, String pharmacyName,
                      LocalDateTime from, LocalDateTime to, AppointmentStatus appointmentStatus) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientEmail = patientEmail;
        this.pharmacyName = pharmacyName;
        this.from = from;
        this.to = to;
        this.appointmentStatus = appointmentStatus.toString();
    }

    public CheckUpDTO(CheckUp checkUp) {
        this.patientFirstName = checkUp.getPatient().getFirstName();
        this.patientLastName = checkUp.getPatient().getLastName();
        this.patientEmail = checkUp.getPatient().getEmail();
        this.pharmacyName = checkUp.getPharmacy().getName();
        this.from = checkUp.getDateTimeRange().getFrom();
        this.to = checkUp.getDateTimeRange().getTo();
        this.appointmentStatus = checkUp.getAppointmentStatus().toString();
        this.pharmacyId = checkUp.getPharmacy().getId();
    }

    public CheckUpDTO() {
        //Empty ctor for JSON serializer
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
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
