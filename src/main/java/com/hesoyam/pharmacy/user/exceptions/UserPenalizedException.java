package com.hesoyam.pharmacy.user.exceptions;

public class UserPenalizedException extends RuntimeException{

    public UserPenalizedException(Long id){
        super(String.format("User with ID '%s' has 3 or more penalty points", id));
    }

}
