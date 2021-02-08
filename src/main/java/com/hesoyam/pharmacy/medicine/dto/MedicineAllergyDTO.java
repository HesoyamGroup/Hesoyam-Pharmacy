package com.hesoyam.pharmacy.medicine.dto;

public class MedicineAllergyDTO {

    Long id;

    public MedicineAllergyDTO() {
        //Empty ctor for JSON serializer
    }

    public MedicineAllergyDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
