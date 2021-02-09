package com.hesoyam.pharmacy.medicine.exceptions;

public class InvalidDeleteMedicineRequestException extends RuntimeException{
    public InvalidDeleteMedicineRequestException(String message){
        super(message);
    }
}
