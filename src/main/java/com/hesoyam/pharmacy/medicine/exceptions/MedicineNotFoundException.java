package com.hesoyam.pharmacy.medicine.exceptions;

public class MedicineNotFoundException extends Exception{

    public MedicineNotFoundException(Long id){
        super(String.format("Medicine with ID '%s' not found."));
    }
}
