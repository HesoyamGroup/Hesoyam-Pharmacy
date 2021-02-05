package com.hesoyam.pharmacy.pharmacy.dto;

import com.hesoyam.pharmacy.location.model.Address;

public class PharmacyWithPrescriptionPriceDTO {
    private Long id;
    private String name;
    private Address address;
    private int price;
    private double rating;

    public PharmacyWithPrescriptionPriceDTO() {
    }

    public PharmacyWithPrescriptionPriceDTO(Long id, String name, Address address, int price, double pharmacyRating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
        this.rating = pharmacyRating;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
