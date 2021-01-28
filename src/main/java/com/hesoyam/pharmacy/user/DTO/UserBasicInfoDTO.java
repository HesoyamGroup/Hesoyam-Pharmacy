package com.hesoyam.pharmacy.user.DTO;

import com.hesoyam.pharmacy.user.model.Gender;

public class UserBasicInfoDTO {
    String firstName;
    String lastName;
    Gender gender;
    String telephone;

    public UserBasicInfoDTO() {
    }

    public UserBasicInfoDTO(String firstName, String lastName, Gender gender, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.telephone = telephone;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
