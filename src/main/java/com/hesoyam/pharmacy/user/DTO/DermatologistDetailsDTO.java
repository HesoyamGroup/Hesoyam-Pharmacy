package com.hesoyam.pharmacy.user.DTO;

import com.hesoyam.pharmacy.pharmacy.DTO.PharmacyDTO;
import com.hesoyam.pharmacy.user.model.Dermatologist;

import java.util.ArrayList;
import java.util.List;

public class DermatologistDetailsDTO extends EmployeeBasicDTO {
    private List<PharmacyDTO> pharmacies;

    public DermatologistDetailsDTO(){
        super();
    }

    public DermatologistDetailsDTO(Dermatologist dermatologist){
        super(dermatologist);
        pharmacies = new ArrayList<>();
        dermatologist.getPharmacies().forEach(pharmacy -> pharmacies.add(new PharmacyDTO(pharmacy)));
    }

    public List<PharmacyDTO> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<PharmacyDTO> pharmacies) {
        this.pharmacies = pharmacies;
    }
}
