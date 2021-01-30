package com.hesoyam.pharmacy.appointment.DTO;

import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.util.DateTimeRange;

import java.time.LocalDateTime;

public class CounselingDTO {
    private String patientEmail;
    private String patientFirstName;
    private String patientLastName;
    private LocalDateTime from;
    private LocalDateTime to;

    public CounselingDTO(String patientEmail, String patientFirstName, String patientLastName, LocalDateTime from, LocalDateTime to) {
        this.patientEmail = patientEmail;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.from = from;
        this.to = to;
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
}
