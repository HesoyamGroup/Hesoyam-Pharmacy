package com.hesoyam.pharmacy.feedback.DTO;

import com.hesoyam.pharmacy.user.model.Employee;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class EmployeeComplaintCreateDTO {
    @NotNull
    @Length(min=10, max=400, message = "Complaint content length must be between 10 and 40 characters.")
    private String body;

    private Long patientId;

    @NotNull(message = "Employee ID must be provided.")
    private Long employeeId;

    public EmployeeComplaintCreateDTO(){}

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getBody() {
        return body;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
