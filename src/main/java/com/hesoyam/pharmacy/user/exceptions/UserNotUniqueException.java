package com.hesoyam.pharmacy.user.exceptions;

public class UserNotUniqueException extends Exception{
    public UserNotUniqueException(String message){
        super(message);
    }
}
