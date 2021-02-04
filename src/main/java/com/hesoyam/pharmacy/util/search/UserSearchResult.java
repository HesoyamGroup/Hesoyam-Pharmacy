package com.hesoyam.pharmacy.util.search;

import com.hesoyam.pharmacy.user.dto.PatientDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserSearchResult {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime dateOfAppointment;
    private double grade;

    public UserSearchResult(String firstName, String lastName, String email, double grade, LocalDateTime dateOfAppointment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.grade = grade;
        this.dateOfAppointment = dateOfAppointment;
    }

    public UserSearchResult() {
    }

    public UserSearchResult(PatientDTO patient, double grade) {
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.email = patient.getEmail();
        this.dateOfAppointment = patient.getDateOfAppointment();
        this.grade = grade;
    }

    public LocalDateTime getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDateTime dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
