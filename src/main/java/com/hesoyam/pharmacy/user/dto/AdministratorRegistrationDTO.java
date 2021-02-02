package com.hesoyam.pharmacy.user.dto;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import javax.validation.constraints.NotNull;

//NOTE: It's not the best way to extend DTOs as they should be as concise as possible.
public class AdministratorRegistrationDTO extends RegistrationDTO{

    @NotNull(message = "Pharmacy must be provided")
    private Pharmacy pharmacy;

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
