package com.hesoyam.pharmacy.user.DTO;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Administrator;

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
