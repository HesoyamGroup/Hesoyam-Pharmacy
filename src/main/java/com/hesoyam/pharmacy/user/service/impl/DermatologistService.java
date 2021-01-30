package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.repository.DermatologistRepository;
import com.hesoyam.pharmacy.user.service.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DermatologistService implements IDermatologistService {
    
    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Override
    public Dermatologist getById(Long id) throws DermatologistNotFoundException {
        return dermatologistRepository.findById(id).orElseThrow(() -> new DermatologistNotFoundException((id)));
    }
     
    @Override
    public List<Dermatologist> getWorkingDermatologistsAtPharmacy(Long id) {
        return dermatologistRepository.findAllDermatologistsByPharmacy(id);
    }

    @Override
    public List<Dermatologist> getAll(User loggedInUser) {
        switch (loggedInUser.getRoleEnum()){
            case PATIENT:
                return dermatologistRepository.findAll();
            case ADMINISTRATOR:
                return dermatologistRepository.findAllByPharmacyAdministrator(loggedInUser.getId());
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public List<Dermatologist> search(User loggedInUser, String firstName, String lastName) {
        List<Dermatologist> dermatologists = dermatologistRepository.findAll();
        switch(loggedInUser.getRoleEnum()){
            case PATIENT:
                return dermatologists.stream().filter(dermatologist -> dermatologist.startsWithName(firstName, lastName)).collect(Collectors.toList());
            case ADMINISTRATOR:
                return dermatologists.stream().filter(dermatologist -> dermatologist.startsWithName(firstName, lastName) && dermatologist.isAdministratorMyBoss(loggedInUser)).collect(Collectors.toList());
            default:
                return new ArrayList<>();
        }
    }
}
