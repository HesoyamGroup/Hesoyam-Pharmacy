package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.exceptions.PharmacistNotFoundException;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.repository.PharmacistRepository;
import com.hesoyam.pharmacy.user.service.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacistService implements IPharmacistService {
    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Override
    public Pharmacist getById(Long id) throws PharmacistNotFoundException {
        return pharmacistRepository.findById(id).orElseThrow(() -> new PharmacistNotFoundException(id));
    }
    
    @Override
    public List<Pharmacist> getWorkingPharmacistsAtPharmacy(Long id) {
        return pharmacistRepository.findAllByPharmacy_Id(id);
    }

    @Override
    public List<Pharmacist> getAll(User loggedInUser) {
        switch (loggedInUser.getRoleEnum()){
            case PATIENT:
                return pharmacistRepository.findAll();
            case ADMINISTRATOR:
                return pharmacistRepository.findAllByPharmacyAdministrator(loggedInUser.getId());
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public List<Pharmacist> search(User loggedInUser, String firstName, String lastName) {
        List<Pharmacist> pharmacists = pharmacistRepository.findAll();
        switch (loggedInUser.getRoleEnum()){
            case PATIENT:
                return pharmacists.stream().filter(pharmacist -> pharmacist.startsWithName(firstName, lastName)).collect(Collectors.toList());
            case ADMINISTRATOR:
                return pharmacists.stream().filter(pharmacist -> pharmacist.startsWithName(firstName, lastName) && pharmacist.isAdministratorMyBoss(loggedInUser)).collect(Collectors.toList());
            default:
                return new ArrayList<>();
        }
    }
}
