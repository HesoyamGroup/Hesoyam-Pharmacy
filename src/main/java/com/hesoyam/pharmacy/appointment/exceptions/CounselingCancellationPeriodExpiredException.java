package com.hesoyam.pharmacy.appointment.exceptions;

public class CounselingCancellationPeriodExpiredException extends Exception{

    public CounselingCancellationPeriodExpiredException(Long id){
        super(String.format("Counseling with ID '%s' cannot be cancelled 24h before selected date!", id));
    }
}
