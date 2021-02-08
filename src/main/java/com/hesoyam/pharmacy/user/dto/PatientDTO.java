package com.hesoyam.pharmacy.user.dto;

import com.hesoyam.pharmacy.appointment.model.Appointment;

import java.time.LocalDateTime;

public class PatientDTO {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime dateOfAppointment;

    public PatientDTO(String firstName, String lastName, String email, LocalDateTime dateOfAppointment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfAppointment = dateOfAppointment;
    }

    public PatientDTO() {
        //Empty ctor for JSON serializer
    }

    public PatientDTO(Appointment appointment) {
        this.firstName = appointment.getPatient().getFirstName();
        this.lastName = appointment.getPatient().getLastName();
        this.email = appointment.getPatient().getEmail();
        this.dateOfAppointment = appointment.getDateTimeRange().getTo();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDateTime dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }
}
