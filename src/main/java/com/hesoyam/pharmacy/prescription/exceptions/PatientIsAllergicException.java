package com.hesoyam.pharmacy.prescription.exceptions;

public class PatientIsAllergicException extends Exception{
    public PatientIsAllergicException(String allergenName){
        super(String.format("Patient is allergic to '%s' !", allergenName));
    }
}
