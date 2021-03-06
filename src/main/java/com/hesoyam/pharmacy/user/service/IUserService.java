package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.dto.AdministratorRegistrationDTO;
import com.hesoyam.pharmacy.user.dto.ChangePasswordDTO;
import com.hesoyam.pharmacy.user.dto.PharmacistRegistrationDTO;
import com.hesoyam.pharmacy.user.dto.RegistrationDTO;
import com.hesoyam.pharmacy.user.exceptions.*;
import com.hesoyam.pharmacy.user.model.*;

import java.util.Collection;

public interface IUserService {
    User findById(Long id) throws UserNotFoundException;
    User findByEmail(String email) throws UserNotFoundException;
    User update(User userData) throws UserNotFoundException;
    User changePassword(ChangePasswordDTO changePasswordDTO) throws UserNotFoundException, InvalidChangePasswordRequestException;
    Collection<User> findAll();
    Patient register(RegistrationDTO registrationDTO) throws RegistrationValidationException, UserNotUniqueException;
    Employee registerEmployeeAccount(RegistrationDTO registrationDTO) throws InvalidRegisterEmployeeRequestException, UserNotUniqueException;
    SysAdmin registerSysAdmin(RegistrationDTO registrationDTO) throws UserNotUniqueException;
    Administrator registerAdministrator(AdministratorRegistrationDTO administratorRegistrationDTO) throws UserNotUniqueException;
    Supplier registerSupplier(RegistrationDTO registrationDTO) throws UserNotUniqueException;
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String token);

    Pharmacist registerPharmacist(PharmacistRegistrationDTO registrationDTO, User user) throws InvalidRegisterEmployeeRequestException, UserNotUniqueException;
}
