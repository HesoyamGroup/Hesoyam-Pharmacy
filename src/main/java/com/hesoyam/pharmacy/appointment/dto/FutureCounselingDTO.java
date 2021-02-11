package com.hesoyam.pharmacy.appointment.dto;

import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.util.DateTimeRange;

public class FutureCounselingDTO {
    Long id;
    String pharmacyName;
    String pharmacistFullName;
    DateTimeRange range;
    Double price;

    public FutureCounselingDTO() {
        //Empty ctor for JSON serializer
    }

    public FutureCounselingDTO(Counseling counseling){
        this.id = counseling.getId();
        this.pharmacyName = counseling.getPharmacy().getName();
        this.pharmacistFullName = counseling.getPharmacist().getFirstName()+" "+counseling.getPharmacist().getLastName();
        this.range = counseling.getDateTimeRange();
        this.price = counseling.getPrice();
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

    public String getPharmacistFullName() {
        return pharmacistFullName;
    }

    public void setPharmacistFullName(String pharmacistFullName) {
        this.pharmacistFullName = pharmacistFullName;
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
