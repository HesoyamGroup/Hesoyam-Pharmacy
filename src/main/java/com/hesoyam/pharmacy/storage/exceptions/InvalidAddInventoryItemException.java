package com.hesoyam.pharmacy.storage.exceptions;

public class InvalidAddInventoryItemException extends RuntimeException{
    public InvalidAddInventoryItemException(String message){
        super(message);
    }
}
