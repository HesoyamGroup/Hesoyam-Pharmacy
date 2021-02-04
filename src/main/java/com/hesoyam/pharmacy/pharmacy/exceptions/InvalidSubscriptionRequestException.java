package com.hesoyam.pharmacy.pharmacy.exceptions;

public class InvalidSubscriptionRequestException extends RuntimeException{
    public InvalidSubscriptionRequestException(String message){
        super(message);
    }
}
