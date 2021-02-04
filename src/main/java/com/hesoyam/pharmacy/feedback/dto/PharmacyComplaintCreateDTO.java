package com.hesoyam.pharmacy.feedback.dto;

import com.hesoyam.pharmacy.user.model.Patient;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class PharmacyComplaintCreateDTO {
    @NotNull
    @Length(min=10, max=400, message = "Complaint content length must be between 10 and 40 characters.")
    private String body;

    private Patient patient;

    @NotNull(message = "Pharmacy ID must be specified.")
    private Long pharmacyId;

    public PharmacyComplaintCreateDTO(){}

    public String getBody() {
        return body;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
