package com.hesoyam.pharmacy.user.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangePasswordDTO {

    @NotBlank
    @Email(message = "A valid email address must be provided.")
    private String email;

    @NotNull
    @Length(min=8, max=64, message = "Password length should be between 8 and 64 characters.")
    private String oldPassword;
    @NotNull
    @Length(min=8, max=64, message = "Password length should be between 8 and 64 characters.")
    private String newPassword;

    private String confirmNewPassword;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
