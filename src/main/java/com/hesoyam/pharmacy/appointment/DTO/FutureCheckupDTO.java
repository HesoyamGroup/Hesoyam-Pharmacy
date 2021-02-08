package com.hesoyam.pharmacy.appointment.dto;

import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.util.DateTimeRange;

public class FutureCheckupDTO {
    Long id;
    String pharmacyName;
    String dermatologistFullName;
    DateTimeRange range;
    Double price;

    public FutureCheckupDTO() {
        //Empty ctor for JSON serializer
    }

    public FutureCheckupDTO(CheckUp checkUp) {
        this.id = checkUp.getId();
        this.pharmacyName = checkUp.getPharmacy().getName();
        this.dermatologistFullName = checkUp.getDermatologist().getFirstName()+" "+checkUp.getDermatologist().getLastName();
        this.range = checkUp.getDateTimeRange();
        this.price = checkUp.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getDermatologistFullName() {
        return dermatologistFullName;
    }

    public void setDermatologistFullName(String dermatologistFullName) {
        this.dermatologistFullName = dermatologistFullName;
    }

    public DateTimeRange getRange() {
        return range;
    }

    public void setRange(DateTimeRange range) {
        this.range = range;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
