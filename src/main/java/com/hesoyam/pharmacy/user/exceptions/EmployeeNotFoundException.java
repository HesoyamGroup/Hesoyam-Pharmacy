package com.hesoyam.pharmacy.user.exceptions;

public class EmployeeNotFoundException extends Exception{

    public EmployeeNotFoundException(Long id){
        super(String.format("Employee with ID '%s' not found", id));
    }
}
