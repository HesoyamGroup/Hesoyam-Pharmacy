package com.hesoyam.pharmacy.user.dto;

import com.hesoyam.pharmacy.location.model.City;

public class AddressDTO {
    String addressLine;
    City city;

    public AddressDTO() {
        //Empty ctor for JSON serializer
    }

    public AddressDTO(String addressLine, City city) {
        this.addressLine = addressLine;
        this.city = city;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
