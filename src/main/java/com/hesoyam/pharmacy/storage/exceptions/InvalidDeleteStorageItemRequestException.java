package com.hesoyam.pharmacy.storage.exceptions;

public class InvalidDeleteStorageItemRequestException extends RuntimeException{
    public InvalidDeleteStorageItemRequestException(String message){
        super(message);
    }
}
