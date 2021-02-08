package com.hesoyam.pharmacy.user.dto;

public class AllergiesDTO {

    Long id;
    String medicineName;
    String manufacturerName;

    public AllergiesDTO() {
        //Empty ctor for JSON serializer
    }

    public AllergiesDTO(Long id, String medicineName, String manufacturerName) {
        this.id = id;
        this.medicineName = medicineName;
        this.manufacturerName = manufacturerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
