package com.hesoyam.pharmacy.prescription.dto;

import com.hesoyam.pharmacy.location.model.Address;

public class PharmacyWithPrescriptionPriceDTO {
    private Long id;
    private String name;
    private Address address;
    private double price;
    private double discountedPrice;
    private double rating;
    private Long eprescriptionId;

    public PharmacyWithPrescriptionPriceDTO() {
    }

    public PharmacyWithPrescriptionPriceDTO(Long id, String name, Address address, double price, double pharmacyRating, Long eprescriptionId, double discountedPrice) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
        this.rating = pharmacyRating;
        this.eprescriptionId = eprescriptionId;
        this.discountedPrice = discountedPrice;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Long getEprescriptionId() {
        return eprescriptionId;
    }

    public void setEprescriptionId(Long eprescriptionId) {
        this.eprescriptionId = eprescriptionId;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
