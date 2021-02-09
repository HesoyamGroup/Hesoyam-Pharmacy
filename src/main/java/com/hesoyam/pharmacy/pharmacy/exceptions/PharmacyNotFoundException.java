package com.hesoyam.pharmacy.pharmacy.exceptions;

public class PharmacyNotFoundException extends Exception {

    public PharmacyNotFoundException(Long id){
        super(String.format("Pharmacy with ID '%s' not found", id));
    }

}
