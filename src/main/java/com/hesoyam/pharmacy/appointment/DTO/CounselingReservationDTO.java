package com.hesoyam.pharmacy.appointment.dto;

import com.hesoyam.pharmacy.appointment.model.Counseling;


public class CounselingReservationDTO {
    Long pharmacyId;
    String pharmacyName;
    String cityName;
    String addressLine;
    Double rating;

    public CounselingReservationDTO() {
        //Empty ctor for JSON serializer
    }

    public CounselingReservationDTO(Counseling counseling){
        this.pharmacyId = counseling.getPharmacy().getId();
        this.pharmacyName = counseling.getPharmacy().getName();
        this.cityName = counseling.getPharmacy().getAddress().getCity().getCityName();
        this.addressLine = counseling.getPharmacy().getAddress().getAddressLine();
        this.rating = counseling.getPharmacy().getRating();
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
