package com.hesoyam.pharmacy.user.dto;

import com.hesoyam.pharmacy.user.model.Gender;
import com.hesoyam.pharmacy.user.model.User;

public class UserProfileDTO {

    String firstName;
    String lastName;
    Gender gender;
    String email;
    String telephone;

    public UserProfileDTO() {
        //Empty ctor for JSON serializer
    }

    public UserProfileDTO(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.telephone = user.getTelephone();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String phone) {
        this.telephone = phone;
    }
}
