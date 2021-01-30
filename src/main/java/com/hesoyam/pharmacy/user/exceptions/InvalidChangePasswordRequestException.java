package com.hesoyam.pharmacy.user.exceptions;

public class InvalidChangePasswordRequestException extends Exception {
    public InvalidChangePasswordRequestException(String message){
        super(message);
    }
}
