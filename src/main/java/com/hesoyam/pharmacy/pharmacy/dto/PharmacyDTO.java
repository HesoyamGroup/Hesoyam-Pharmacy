package com.hesoyam.pharmacy.pharmacy.dto;

import com.hesoyam.pharmacy.location.model.Address;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

public class PharmacyDTO {
    private Long id;
    private String name;
    private Address address;
    private double rating;

    public PharmacyDTO(){

    }

    public PharmacyDTO(Pharmacy pharmacy){
        this.id = pharmacy.getId();
        this.name = pharmacy.getName();
        this.address = pharmacy.getAddress();
        this.rating = pharmacy.getRating();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
