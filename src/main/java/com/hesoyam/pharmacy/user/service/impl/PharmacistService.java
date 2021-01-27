package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.repository.PharmacistRepository;
import com.hesoyam.pharmacy.user.service.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacistService implements IPharmacistService {
    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Override
    public List<Pharmacist> getWorkingPharmacistsAtPharmacy(Long id) {
        return pharmacistRepository.findAllByPharmacy_Id(id);
    }
}
