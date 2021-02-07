package com.hesoyam.pharmacy.appointment.exceptions;


public class CounselingNotFoundException extends Exception{
    public CounselingNotFoundException(Long id){
        super(String.format("Counseling with ID (or for patient ID) '%s' not found", id));
	}
}
