package com.hesoyam.pharmacy.pharmacy.DTO;

import com.hesoyam.pharmacy.location.model.Address;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class PharmacyCreateDTO {
    @NotNull(message = "Pharmacy name must be provided.")
    @Length(min=2, max=75, message = "Pharmacy name length should be between 2 and 75 characters.")
    private String name;

    @NotNull
    @Length(max=300, message = "Pharmacy description length should not exceed 300 characters.")
    private String description;

    @NotNull
    private Address address;

    public PharmacyCreateDTO() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
