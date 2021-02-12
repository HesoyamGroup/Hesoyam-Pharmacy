package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.dto.DermatologistAddPharmacyDTO;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.repository.AdministratorRepository;
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

    @Autowired
    private AdministratorRepository administratorRepository;

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

    @Override
    public List<Dermatologist> getUnsortedDermatologistsByAdministrator(User user) {
        Administrator administrator = administratorRepository.getOne(user.getId());
        Pharmacy searchPharmacy = administrator.getPharmacy();
        List<Dermatologist> dermatologists = dermatologistRepository.findAll();
        return dermatologists.stream().filter(dermatologist -> !dermatologist.isWorkingAt(searchPharmacy)).collect(Collectors.toList());
    }

    @Override
    public Dermatologist addDermatologistToAdministratorPharmacy(User user, DermatologistAddPharmacyDTO dermatologist) throws IllegalAccessException {
        Administrator administrator = administratorRepository.getOne(user.getId());
        Pharmacy pharmacy = administrator.getPharmacy();
        Dermatologist dermatologistToAdd = dermatologistRepository.getOne(dermatologist.getId());

        if(!dermatologistToAdd.addPharmacy(pharmacy))
            throw new IllegalAccessException("Pharmacy is already added to dermatologist");

        if(!dermatologistToAdd.generateShifts(dermatologist.getShiftRange(), dermatologist.getShiftFrom(), dermatologist.getShiftTo(), pharmacy))
            throw new IllegalArgumentException("Dermatologist has already defined shifts for this period");

        return dermatologistRepository.save(dermatologistToAdd);
    }
}
