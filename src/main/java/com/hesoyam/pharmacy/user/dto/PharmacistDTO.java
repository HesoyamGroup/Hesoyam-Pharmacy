package com.hesoyam.pharmacy.user.dto;

public class PharmacistDTO {
    private String firstName;
    private String lastName;
    private String pharmacyName;

    public PharmacistDTO(){}

    public PharmacistDTO(String firstName, String lastName, String pharmacyName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.pharmacyName = pharmacyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}
