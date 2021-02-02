package com.hesoyam.pharmacy.user.dto;

public class PasswordDTO {
    String password;
    String confirmPassword;
    String oldPassword;

    public PasswordDTO() {
    }

    public PasswordDTO(String password, String confirmPassword, String oldPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.oldPassword = oldPassword;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
