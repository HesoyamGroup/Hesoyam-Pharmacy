package com.hesoyam.pharmacy.user.exceptions;

public class PharmacistNotFoundException extends Exception{
    public PharmacistNotFoundException(Long userID){
        super(String.format("A pharmacist with ID '%s' could not be found.", userID));
    }

    public PharmacistNotFoundException(String email){
        super(String.format("A pharmacist with email '%s' could not be found.", email));
    }
}
