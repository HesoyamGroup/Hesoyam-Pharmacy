package com.hesoyam.pharmacy.user.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(Long userID){
        super(String.format("An user with ID '%s' could not be found.", userID));
    }

    public UserNotFoundException(String email){
        super(String.format("An user with email '%s' could not be found.", email));
    }
}
