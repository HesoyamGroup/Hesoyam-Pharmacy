package com.hesoyam.pharmacy.appointment.exceptions;

public class CheckupNotFoundException extends Exception{

    public CheckupNotFoundException(Long id){
        super(String.format("Check-up with ID '%s' not found", id));
    }
}
