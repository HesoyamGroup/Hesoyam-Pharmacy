package com.hesoyam.pharmacy.appointment.DTO;

import java.time.LocalDateTime;

public class CancelledAppointmentDTO {
    private String patientEmail;
    private LocalDateTime from;

    public CancelledAppointmentDTO(String patientEmail, LocalDateTime from) {
        this.patientEmail = patientEmail;
        this.from = from;
    }

    public CancelledAppointmentDTO() {
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
}
