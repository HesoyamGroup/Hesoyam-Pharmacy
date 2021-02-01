package com.hesoyam.pharmacy.medicine.DTO;

import com.hesoyam.pharmacy.medicine.model.MedicineType;

public class MedicineSearchDTO {
    private String name;
    private MedicineType medicineType;
    private Double minRating;
    private Double maxRating;

    public MedicineSearchDTO() {
    }

    public MedicineSearchDTO(String name, MedicineType medicineType, Double minRating, Double maxRating) {
        this.name = name;
        this.medicineType = medicineType;
        this.minRating = minRating;
        this.maxRating = maxRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MedicineType getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
    }

    public Double getMinRating() {
        return minRating;
    }

    public void setMinRating(Double minRating) {
        this.minRating = minRating;
    }

    public Double getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(Double maxRating) {
        this.maxRating = maxRating;
    }
}
