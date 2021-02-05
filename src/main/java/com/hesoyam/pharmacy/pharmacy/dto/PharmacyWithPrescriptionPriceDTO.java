package com.hesoyam.pharmacy.pharmacy.dto;

import com.hesoyam.pharmacy.location.model.Address;

public class PharmacyWithPrescriptionPriceDTO {
    private Long id;
    private String name;
    private Address address;
    private int price;

    public PharmacyWithPrescriptionPriceDTO() {
    }

    public PharmacyWithPrescriptionPriceDTO(Long id, String name, Address address, int price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
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
}
