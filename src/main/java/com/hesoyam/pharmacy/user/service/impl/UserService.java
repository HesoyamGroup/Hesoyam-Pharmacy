package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.DTO.RegistrationDTO;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Role;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.repository.UserRepository;
import com.hesoyam.pharmacy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserDetailsService, IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElseGet(null);
        return user;
    }

    @Override
    public User findByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public Collection<User> findAll() {
        Collection<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public Patient save(RegistrationDTO registrationDTO) {
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

        patient = userRepository.save(patient);

        return patient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }else{
            return user;
        }
    }

}
