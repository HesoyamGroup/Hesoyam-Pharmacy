package com.hesoyam.pharmacy.user.exceptions;

public class DermatologistNotFoundException extends Exception{
    public DermatologistNotFoundException(Long userID){
        super(String.format("A dermatologist with ID '%s' could not be found.", userID));
    }

    public DermatologistNotFoundException(String email){
        super(String.format("A dermatologist with email '%s' could not be found.", email));
    }
}
