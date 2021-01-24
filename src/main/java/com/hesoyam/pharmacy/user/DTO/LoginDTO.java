package com.hesoyam.pharmacy.user.DTO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginDTO {
    @NotBlank
    @Email(message = "A valid email address must be provided.")
    private String email;

    @NotNull
    @Length(min=8, max=64, message = "Password length should be between 8 and 64 characters.")
    private String password;

    public LoginDTO(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
