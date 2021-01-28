package com.hesoyam.pharmacy.feedback.DTO;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Employee;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class PharmacyComplaintCreateDTO {
    @NotNull
    @Length(min=10, max=400, message = "Complaint content length must be between 10 and 40 characters.")
    private String body;


    @NotNull(message = "Pharmacy must be specified.")
    private Pharmacy pharmacy;

    public PharmacyComplaintCreateDTO(){}

    public String getBody() {
        return body;
    }
    public Pharmacy getPharmacy() {
        return pharmacy;
    }

}
