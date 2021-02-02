package com.hesoyam.pharmacy.medicine.exceptions;

public class MedicineReservationNotCreatedException extends Exception{
    public MedicineReservationNotCreatedException(Long id){
        super(String.format("Medicine reservation with ID '%s' does not have status 'CREATED' ", id));
    }
}
