package com.hesoyam.pharmacy.user.exceptions;

public class PatientAlreadyAllergicException extends Exception{

    public PatientAlreadyAllergicException(Long id){
        super(String.format("Allergy on Medicine with ID '%s' cannot be added because Patient already is allergic to it.", id));
    }
}
