package com.hesoyam.pharmacy.appointment.DTO;

import java.time.LocalDateTime;

public class AppointmentBookingDTO {
    private String patientEmail;
    private LocalDateTime from;
    private LocalDateTime to;
    private long pharmacyId;
    private double price;

    public AppointmentBookingDTO(String patientEmail, LocalDateTime from, LocalDateTime to, long pharmacyId, double price) {
        this.patientEmail = patientEmail;
        this.from = from;
        this.to = to;
        this.pharmacyId = pharmacyId;
        this.price = price;
    }

    public AppointmentBookingDTO() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
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

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
}
