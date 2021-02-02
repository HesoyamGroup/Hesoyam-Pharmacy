package com.hesoyam.pharmacy.user.dto;

import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Gender;

public class DermatologistBasicInformationDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String telephone;
    private Gender gender;

    public DermatologistBasicInformationDTO(String email, String firstName, String lastName, String city, String country, String telephone, Gender gender) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.telephone = telephone;
        this.gender = gender;
    }

    public DermatologistBasicInformationDTO() {
    }

    public DermatologistBasicInformationDTO(Dermatologist dermatologist) {
        this.email = dermatologist.getEmail();
        this.firstName = dermatologist.getFirstName();
        this.lastName = dermatologist.getLastName();
        this.telephone = dermatologist.getTelephone();
        this.gender = dermatologist.getGender();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
