package com.hesoyam.pharmacy.user.exceptions;

public class RegistrationUserNotUniqueException extends Exception{
    public RegistrationUserNotUniqueException(String message){
        super(message);
    }
}
