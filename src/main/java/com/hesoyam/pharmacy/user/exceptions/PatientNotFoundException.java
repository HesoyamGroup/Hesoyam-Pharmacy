package com.hesoyam.pharmacy.user.exceptions;

public class PatientNotFoundException extends Exception{
    public PatientNotFoundException(Long userID){
        super(String.format("A patient with ID '%s' could not be found.", userID));
    }

    public PatientNotFoundException(String email){
        super(String.format("A patient with email '%s' could not be found.", email));
    }
}
