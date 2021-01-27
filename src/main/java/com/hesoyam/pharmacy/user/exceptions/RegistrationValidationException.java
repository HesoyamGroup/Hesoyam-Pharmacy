package com.hesoyam.pharmacy.user.exceptions;

import java.util.HashMap;
import java.util.Map;

public class RegistrationValidationException extends Exception{
    private Map<String, String> errorMessages;
    public RegistrationValidationException(Map<String, String> errorMap) {
        super("Multiple errors exception, check GetErrorMessagesMap().");
        errorMessages = errorMap == null ? new HashMap<>() : errorMap;
    }

    public Map<String, String> getErrorMessagesMap(){
        return errorMessages;
    }
}
