package com.hesoyam.pharmacy.appointment.dto;

import com.hesoyam.pharmacy.appointment.model.Counseling;

import java.time.LocalDateTime;

public class CounselingDTO {
    private String patientEmail;
    private String patientFirstName;
    private String patientLastName;
    private LocalDateTime from;
    private LocalDateTime to;
    private String appointmentStatus;
    private long pharmacyId;


    public CounselingDTO(String patientEmail, String patientFirstName, String patientLastName, LocalDateTime from, LocalDateTime to, String appointmentStatus) {
        this.patientEmail = patientEmail;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.from = from;
        this.to = to;
        this.appointmentStatus = appointmentStatus;
    }

    public CounselingDTO(Counseling counseling){
        this.patientEmail = counseling.getPatient().getEmail();
        this.patientFirstName = counseling.getPatient().getFirstName();
        this.patientLastName = counseling.getPatient().getLastName();
        this.from = counseling.getDateTimeRange().getFrom();
        this.to = counseling.getDateTimeRange().getTo();
        this.appointmentStatus = counseling.getAppointmentStatus().toString();
        this.pharmacyId = counseling.getPharmacy().getId();
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public CounselingDTO() {
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

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
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

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
}
