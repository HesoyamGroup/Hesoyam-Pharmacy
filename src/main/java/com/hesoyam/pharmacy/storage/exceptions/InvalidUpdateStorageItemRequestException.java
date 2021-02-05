package com.hesoyam.pharmacy.storage.exceptions;

public class InvalidUpdateStorageItemRequestException extends RuntimeException{
    public InvalidUpdateStorageItemRequestException(String message){
        super(message);
    }
}
