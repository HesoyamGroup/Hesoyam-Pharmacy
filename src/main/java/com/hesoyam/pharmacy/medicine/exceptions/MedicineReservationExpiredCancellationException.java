package com.hesoyam.pharmacy.medicine.exceptions;

public class MedicineReservationExpiredCancellationException extends Exception{

    public MedicineReservationExpiredCancellationException(Long id){
        super(String.format("Reservation with ID '%s' cannot be cancelled. Reason: reservation cannot be cancelled 24h before pickup date.", id));
    }
}
