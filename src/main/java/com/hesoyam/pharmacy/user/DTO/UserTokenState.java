package com.hesoyam.pharmacy.user.DTO;

//DTO which encapsulates generated JWT and expire date.
public class UserTokenState {
    private String token;
    private Long expiresIn;

    public UserTokenState(){
    }

    public UserTokenState(String token, long expiresIn){
        this.token = token;
        this.expiresIn = expiresIn;
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
}
