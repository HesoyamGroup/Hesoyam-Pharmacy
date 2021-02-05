package com.hesoyam.pharmacy.user.exceptions;

public class PatientAllergyNotFoundException extends Exception{

    public PatientAllergyNotFoundException(Long id){
        super(String.format("Patient does not have allergy on Medicine with ID '%s'",id));
    }
}
