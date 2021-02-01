package com.hesoyam.pharmacy.medicine.dto;

import com.hesoyam.pharmacy.medicine.model.MedicineType;

public class MedicineSearchDTO {

    private String name;
    private MedicineType medicineType;
    private Double minRating;
    private Double maxRating;

    private int page;

    public MedicineSearchDTO() {
    }

    public MedicineSearchDTO(String name, MedicineType medicineType, Double minRating, Double maxRating, int page) {
        this.name = name;
        this.medicineType = medicineType;
        this.minRating = minRating;
        this.maxRating = maxRating;
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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
