package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.dto.AdministratorRegistrationDTO;
import com.hesoyam.pharmacy.user.dto.ChangePasswordDTO;
import com.hesoyam.pharmacy.user.dto.RegistrationDTO;
import com.hesoyam.pharmacy.user.exceptions.*;
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

    private static final String EMAIL_ALREADY_TAKEN_ERROR = "A user with specified email already exists.";

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
        return userRepository.save(user);
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
        loadUserAccountWithRegistrationData(patient,registrationDTO, false, false);
        patient.setRoleEnum(RoleEnum.PATIENT);

        //TODO: Find by name parameter should be saved somewhere globally.
        List<Role> roles = (List<Role>) roleService.findByName("ROLE_PATIENT");
        patient.setAuthorities(roles);

        try {
            patient = userRepository.save(patient);
        }catch (DataIntegrityViolationException ex){
            throw new UserNotUniqueException(EMAIL_ALREADY_TAKEN_ERROR);
        }

        return patient;
    }

    @Override
    public Employee registerEmployeeAccount(RegistrationDTO registrationDTO) throws InvalidRegisterEmployeeRequestException, UserNotUniqueException {
        Employee employee = getEmployeeProfile(registrationDTO);
        try{
            return userRepository.save(employee);
        }catch (DataIntegrityViolationException ex){
            throw new UserNotUniqueException(EMAIL_ALREADY_TAKEN_ERROR);
        }
    }

    private Employee getEmployeeProfile(RegistrationDTO registrationDTO) throws InvalidRegisterEmployeeRequestException {
        RoleEnum roleEnum = registrationDTO.getRoleEnum();
        Employee employee;
        if(roleEnum == null) throw new InvalidRegisterEmployeeRequestException("Invalid role specified.");
        switch(roleEnum){
            case DERMATOLOGIST:{
                employee = createDermatologistAccount(registrationDTO);
                break;
            }
            case PHARMACIST:{
                employee = createPharmacistAccount(registrationDTO);
                break;
            }
            default:{
                throw new InvalidRegisterEmployeeRequestException("Invalid role specified.");
            }
        }

        return employee;
    }


    @Override
    public SysAdmin registerSysAdmin(RegistrationDTO registrationDTO) throws UserNotUniqueException {
        SysAdmin sysAdmin = new SysAdmin();
        loadUserAccountWithRegistrationData(sysAdmin, registrationDTO, true, true);
        sysAdmin.setRoleEnum(RoleEnum.SYS_ADMIN);

        //TODO: Find by name parameter should be saved somewhere globally.
        List<Role> roles = (List<Role>) roleService.findByName("ROLE_SYS_ADMIN");
        sysAdmin.setAuthorities(roles);

        try{
            sysAdmin = userRepository.save(sysAdmin);
        }catch (DataIntegrityViolationException ex){
            throw new UserNotUniqueException(EMAIL_ALREADY_TAKEN_ERROR);
        }

        return sysAdmin;
    }



    private Dermatologist createDermatologistAccount(RegistrationDTO registrationDTO){
        Dermatologist dermatologist = new Dermatologist();
        loadUserAccountWithRegistrationData(dermatologist, registrationDTO, false, true);
        dermatologist.setRoleEnum(RoleEnum.DERMATOLOGIST);
        List<Role> roles = (List<Role>) roleService.findByName("ROLE_DERMATOLOGIST");
        dermatologist.setAuthorities(roles);
        return dermatologist;
    }

    private Pharmacist createPharmacistAccount(RegistrationDTO registrationDTO){
        Pharmacist pharmacist = new Pharmacist();
        loadUserAccountWithRegistrationData(pharmacist, registrationDTO, false, true);
        pharmacist.setRoleEnum(RoleEnum.PHARMACIST);
        List<Role> roles = (List<Role>) roleService.findByName("ROLE_PHARMACIST");
        pharmacist.setAuthorities(roles);
        return pharmacist;
    }


    @Override
    public Administrator registerAdministrator(AdministratorRegistrationDTO administratorRegistrationDTO) throws UserNotUniqueException {
        Administrator administrator = new Administrator();
        loadUserAccountWithRegistrationData(administrator, administratorRegistrationDTO, false, true);
        administrator.setRoleEnum(RoleEnum.ADMINISTRATOR);
        administrator.setPharmacy(administratorRegistrationDTO.getPharmacy());

        //TODO: Find by name parameter should be saved somewhere globally.
        List<Role> roles = (List<Role>) roleService.findByName("ROLE_ADMINISTRATOR");
        administrator.setAuthorities(roles);

        try{
            administrator = userRepository.save(administrator);
        }catch (DataIntegrityViolationException ex){
            throw new UserNotUniqueException(EMAIL_ALREADY_TAKEN_ERROR);
        }

        return administrator;
    }

    @Override
    public Supplier registerSupplier(RegistrationDTO registrationDTO) throws UserNotUniqueException {
        Supplier supplier = new Supplier();
        loadUserAccountWithRegistrationData(supplier, registrationDTO, true, true);
        supplier.setRoleEnum(RoleEnum.SUPPLIER);
        List<Role> roles = (List<Role>) roleService.findByName("ROLE_SUPPLIER");
        supplier.setAuthorities(roles);

        try{
            supplier = userRepository.save(supplier);
        }catch (DataIntegrityViolationException ex){
            throw new UserNotUniqueException(EMAIL_ALREADY_TAKEN_ERROR);
        }

        return supplier;
    }

    private void loadUserAccountWithRegistrationData(User user, RegistrationDTO registrationDTO, boolean passwordReset, boolean isEnabled) {
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setGender(registrationDTO.getGender());
        user.setTelephone(registrationDTO.getTelephone());
        user.setAddress(registrationDTO.getAddress());
        user.setLastPasswordResetDate(new Timestamp((new Date().getTime())));
        user.setPasswordReset(passwordReset);
        user.setEnabled(isEnabled);
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
