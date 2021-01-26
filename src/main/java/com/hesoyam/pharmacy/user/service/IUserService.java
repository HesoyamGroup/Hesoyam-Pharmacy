package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.DTO.ChangePasswordDTO;
import com.hesoyam.pharmacy.user.DTO.RegistrationDTO;
import com.hesoyam.pharmacy.user.exceptions.InvalidChangePasswordRequestException;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserNotUniqueException;
import com.hesoyam.pharmacy.user.exceptions.RegistrationValidationException;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.SysAdmin;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.model.VerificationToken;

import java.util.Collection;

public interface IUserService {
    User findById(Long id) throws UserNotFoundException;
    User findByEmail(String email) throws UserNotFoundException;
    User update(User userData) throws UserNotFoundException;
    User changePassword(ChangePasswordDTO changePasswordDTO) throws UserNotFoundException, InvalidChangePasswordRequestException;
    Collection<User> findAll();
    Patient register(RegistrationDTO registrationDTO) throws RegistrationValidationException, UserNotUniqueException;
    SysAdmin registerSysAdmin(RegistrationDTO registrationDTO) throws UserNotUniqueException;
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String token);
}
