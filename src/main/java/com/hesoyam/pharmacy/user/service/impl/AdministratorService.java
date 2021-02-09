package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.repository.AdministratorRepository;
import com.hesoyam.pharmacy.user.service.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService implements IAdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public List<Administrator> getAdministratorsForPharmacyId(long pharmacyId) {
        return administratorRepository.findAllByPharmacy_Id(pharmacyId);
    }
}
