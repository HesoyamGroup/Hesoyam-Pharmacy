package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.DTO.ChangePasswordDTO;
import com.hesoyam.pharmacy.user.DTO.RegistrationDTO;
import com.hesoyam.pharmacy.user.exceptions.InvalidChangePasswordRequestException;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserNotUniqueException;
import com.hesoyam.pharmacy.user.exceptions.RegistrationValidationException;
import com.hesoyam.pharmacy.user.model.*;
import com.hesoyam.pharmacy.user.repository.UserRepository;
import com.hesoyam.pharmacy.user.repository.VerificationTokensRepository;
import com.hesoyam.pharmacy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserService implements UserDetailsService, IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private VerificationTokensRepository verificationTokensRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User update(User userData) throws UserNotFoundException {
        //TODO: Authorization needs to be done here. Do NOT forget.
        //TODO: Test the method furthermore.
        User user = userRepository.getOne(userData.getId());
        if(user == null) throw new UserNotFoundException(userData.getId());
        user.update(userData);
        user = userRepository.save(user);

        return user;
    }

    @Override
    public User changePassword(ChangePasswordDTO changePasswordDTO) throws InvalidChangePasswordRequestException {
        validateChangePasswordRequest(changePasswordDTO);
        String email = changePasswordDTO.getEmail();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new InvalidChangePasswordRequestException("User not found."));
        if(!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())){
            throw new InvalidChangePasswordRequestException("Incorrect password.");
        }

        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        user.setPasswordReset(false);
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    private void validateChangePasswordRequest(ChangePasswordDTO changePasswordDTO) throws InvalidChangePasswordRequestException {
        if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())){
            throw new InvalidChangePasswordRequestException("Passwords must match.");
        }
    }


    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Patient register(RegistrationDTO registrationDTO) throws RegistrationValidationException, UserNotUniqueException {
        validateRegistrationRequest(registrationDTO);

        Patient patient = new Patient();
        patient.setEmail(registrationDTO.getEmail());
        patient.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        patient.setFirstName(registrationDTO.getFirstName());
        patient.setLastName(registrationDTO.getLastName());
        patient.setGender(registrationDTO.getGender());
        patient.setTelephone(registrationDTO.getTelephone());
        patient.setAddress(registrationDTO.getAddress());
        patient.setLastPasswordResetDate(new Timestamp((new Date().getTime())));

        //TODO: Find by name parameter should be saved somewhere globally.
        List<Role> roles = (List<Role>) roleService.findByName("ROLE_PATIENT");
        patient.setAuthorities(roles);

        try {
            patient = userRepository.save(patient);
        }catch (DataIntegrityViolationException ex){
            throw new UserNotUniqueException("A user with specified email already exists.");
        }

        return patient;
    }

    @Override
    public SysAdmin registerSysAdmin(RegistrationDTO registrationDTO) throws UserNotUniqueException {
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setEmail(registrationDTO.getEmail());
        sysAdmin.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        sysAdmin.setFirstName(registrationDTO.getFirstName());
        sysAdmin.setLastName(registrationDTO.getLastName());
        sysAdmin.setGender(registrationDTO.getGender());
        sysAdmin.setTelephone(registrationDTO.getTelephone());
        sysAdmin.setAddress(registrationDTO.getAddress());
        sysAdmin.setLastPasswordResetDate(new Timestamp((new Date().getTime())));
        sysAdmin.setPasswordReset(true);

        //TODO: Find by name parameter should be saved somewhere globally.
        List<Role> roles = (List<Role>) roleService.findByName("ROLE_SYS_ADMIN");
        sysAdmin.setAuthorities(roles);

        try{
            sysAdmin = userRepository.save(sysAdmin);
        }catch (DataIntegrityViolationException ex){
            throw new UserNotUniqueException("A user with specified email already exists.");
        }

        return sysAdmin;
    }


    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken userToken = new VerificationToken(user, token);
        verificationTokensRepository.save(userToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return verificationTokensRepository.getByToken(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user;
        try{
            user = this.findByEmail(username);
        }catch (UserNotFoundException e){
            user = null;
        }

        if(user == null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }else{
            return user;
        }
    }


    private void validateRegistrationRequest(RegistrationDTO registrationDTO) throws RegistrationValidationException{
        HashMap<String, String> errorMap = new HashMap<>();
        if(!checkPasswordMatch(registrationDTO)) errorMap.put("passwordsNotMatch", "Passwords must match.");

        if(errorMap.size() != 0) throw new RegistrationValidationException(errorMap);
    }


    private boolean checkPasswordMatch(RegistrationDTO registrationDTO){
        return registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword());
    }



}
