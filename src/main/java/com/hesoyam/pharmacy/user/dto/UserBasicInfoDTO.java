package com.hesoyam.pharmacy.user.dto;

import com.hesoyam.pharmacy.user.model.Gender;
import com.hesoyam.pharmacy.user.model.User;

public class UserBasicInfoDTO {
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected Gender gender;
    protected String telephone;

    public UserBasicInfoDTO() {
        //Empty ctor for JSON serializer
    }

    public UserBasicInfoDTO(String firstName, String lastName, Gender gender, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.telephone = telephone;
    }

    public UserBasicInfoDTO(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.telephone = user.getTelephone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
