package com.hesoyam.pharmacy.user.exceptions;

import java.util.HashMap;
import java.util.Map;

public class RegistrationValidationException extends Exception{
    Map<String, String> errorMessages;
    public RegistrationValidationException(HashMap<String, String> error_map) {
        super("Multiple errors exception, check GetErrorMessagesMap().");
        errorMessages = error_map == null ? new HashMap<>() : error_map;
    }

    public Map<String, String> getErrorMessagesMap(){
        return errorMessages;
    }
}
