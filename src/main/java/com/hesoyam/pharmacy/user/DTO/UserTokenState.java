package com.hesoyam.pharmacy.user.DTO;

import com.hesoyam.pharmacy.user.model.Role;

//DTO which encapsulates generated JWT and expire date.
public class UserTokenState {
    private String token;
    private Long expiresIn;
    private Role role;

    public UserTokenState(){
    }

    public UserTokenState(String token, long expiresIn, Role role){
        this.token = token;
        this.expiresIn = expiresIn;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
