package com.hesoyam.pharmacy.medicine.dto;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineType;

public class MedicineBasicInfoDTO {
    private Long id;
    private String name;
    private MedicineType type;
    private String manufacturerName;

    public MedicineBasicInfoDTO(){
        //Empty ctor for JSON serializer
    }

    public MedicineBasicInfoDTO(Medicine medicine){
        this.id = medicine.getId();
        this.name = medicine.getName();
        this.type = medicine.getMedicineType();
        this.manufacturerName = medicine.getManufacturer().getName();
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

    public MedicineType getType() {
        return type;
    }

    public void setType(MedicineType type) {
        this.type = type;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
