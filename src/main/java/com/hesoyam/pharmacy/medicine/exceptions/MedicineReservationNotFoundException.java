package com.hesoyam.pharmacy.medicine.exceptions;

public class MedicineReservationNotFoundException extends Exception {
    public MedicineReservationNotFoundException(Long id) {
        super(String.format("A medicine reservation with ID '%s' could not be found.", id));
    }
}
