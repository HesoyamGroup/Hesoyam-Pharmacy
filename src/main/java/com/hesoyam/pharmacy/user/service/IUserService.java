package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.DTO.RegistrationDTO;
import com.hesoyam.pharmacy.user.exceptions.RegistrationUserNotUniqueException;
import com.hesoyam.pharmacy.user.exceptions.RegistrationValidationException;
import com.hesoyam.pharmacy.user.model.User;

import java.util.Collection;

public interface IUserService {
    User findById(Long id);
    User findByEmail(String email);
    Collection<User> findAll();
    User save(RegistrationDTO registrationDTO) throws RegistrationValidationException, RegistrationUserNotUniqueException;
}
