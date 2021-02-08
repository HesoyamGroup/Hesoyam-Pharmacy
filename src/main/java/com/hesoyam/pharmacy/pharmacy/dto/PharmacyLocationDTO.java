package com.hesoyam.pharmacy.pharmacy.dto;

public class PharmacyLocationDTO {
    Double lat;
    Double lng;

    public PharmacyLocationDTO(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public PharmacyLocationDTO(){
        //Empty ctor for JSON serializer
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
