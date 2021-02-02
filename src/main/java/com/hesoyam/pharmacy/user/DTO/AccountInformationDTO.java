package com.hesoyam.pharmacy.user.dto;

public class AccountInformationDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String oldPassword;

    public AccountInformationDTO() {
    }

    public AccountInformationDTO(String email, String password, String confirmPassword, String oldPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return this.password;
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

    public String getEmail() { return email; }
}
