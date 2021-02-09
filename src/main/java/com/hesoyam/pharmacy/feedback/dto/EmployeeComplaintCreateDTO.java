package com.hesoyam.pharmacy.feedback.dto;
import com.hesoyam.pharmacy.user.model.Patient;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class EmployeeComplaintCreateDTO {
    @NotNull
    @Length(min=10, max=400, message = "Complaint content length must be between 10 and 40 characters.")
    private String body;

    private Patient patient;

    @NotNull(message = "Employee ID must be provided.")
    private Long employeeId;

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getBody() {
        return body;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
