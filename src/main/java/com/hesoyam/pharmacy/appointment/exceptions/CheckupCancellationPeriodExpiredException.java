package com.hesoyam.pharmacy.appointment.exceptions;

public class CheckupCancellationPeriodExpiredException extends Exception{

    public CheckupCancellationPeriodExpiredException(Long id){
        super(String.format("Checkup with ID '%s' cannot be cancelled 24h before reserved time",id));
    }

}
