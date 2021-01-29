package com.hesoyam.pharmacy.user.DTO;

import com.hesoyam.pharmacy.pharmacy.DTO.PharmacyDTO;
import com.hesoyam.pharmacy.user.model.Pharmacist;

public class PharmacistDetailsDTO extends EmployeeBasicDTO {

    private PharmacyDTO pharmacy;

    public PharmacistDetailsDTO(){
        super();
    }

    public PharmacistDetailsDTO(Pharmacist pharmacist) {
        super(pharmacist);
        this.pharmacy = new PharmacyDTO(pharmacist.getPharmacy());
    }

    public PharmacyDTO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }
}
