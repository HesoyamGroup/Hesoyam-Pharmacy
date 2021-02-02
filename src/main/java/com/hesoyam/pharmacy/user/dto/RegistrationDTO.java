package com.hesoyam.pharmacy.user.dto;

import com.hesoyam.pharmacy.location.model.Address;
import com.hesoyam.pharmacy.user.model.Gender;
import com.hesoyam.pharmacy.user.model.RoleEnum;
import com.hesoyam.pharmacy.user.validators.PhoneNumberConstraint;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegistrationDTO {
    @NotNull(message="First name must be provided")
    @Length(min=2, max=75, message = "First name length should be between 2 and 75 characters.")
    protected String firstName;

    @NotNull(message = "Last name must be provided.")
    @Length(min=3, max = 100, message = "Last name length should be between 3 and 100 characters.")
    protected String lastName;

    @PhoneNumberConstraint
    protected String telephone;

    @NotBlank
    @Email(message = "A valid email address must be provided.")
    protected String email;

    @NotNull
    protected Gender gender;

    @NotNull
    @Length(min=8, max=64, message = "Password length should be between 8 and 64 characters.")
    protected String password;

    @NotNull
    protected String confirmPassword;

    @NotNull
    protected Address address;

    protected RoleEnum roleEnum;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}
