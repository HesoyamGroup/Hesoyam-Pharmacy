package com.hesoyam.pharmacy.medicine.DTO;

import com.hesoyam.pharmacy.medicine.model.MedicineType;
import com.hesoyam.pharmacy.medicine.model.PrescriptionMode;

public class MedicineSearchResultDTO {
    private Long id;
    private String name;
    private String manufacturerName;
    private MedicineType medicineType;
    private PrescriptionMode prescriptionMode;
    private double rating;
    private Long medicineSpecificationId;
    private String notes;

    public MedicineSearchResultDTO(){

    }

    public MedicineSearchResultDTO(Long id, String name, String manufacturerName, MedicineType medicineType, PrescriptionMode prescriptionMode, double rating, Long medicineSpecificationId, String notes) {
        this.id = id;
        this.name = name;
        this.manufacturerName = manufacturerName;
        this.medicineType = medicineType;
        this.prescriptionMode = prescriptionMode;
        this.rating = rating;
        this.medicineSpecificationId = medicineSpecificationId;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public MedicineType getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
    }

    public PrescriptionMode getPrescriptionMode() {
        return prescriptionMode;
    }

    public void setPrescriptionMode(PrescriptionMode prescriptionMode) {
        this.prescriptionMode = prescriptionMode;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Long getMedicineSpecificationId() {
        return medicineSpecificationId;
    }

    public void setMedicineSpecificationId(Long medicineSpecificationId) {
        this.medicineSpecificationId = medicineSpecificationId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
