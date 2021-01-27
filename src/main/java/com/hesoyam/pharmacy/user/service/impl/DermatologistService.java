package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.repository.DermatologistRepository;
import com.hesoyam.pharmacy.user.service.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermatologistService implements IDermatologistService {
    
    @Autowired
    DermatologistRepository dermatologistRepository;

    @Override
    public Dermatologist getById(Long id) throws DermatologistNotFoundException {
        return dermatologistRepository.findById(id).orElseThrow(() -> new DermatologistNotFoundException((id)));
    }
     
    @Override
    public List<Dermatologist> getWorkingDermatologistsAtPharmacy(Long id) {
        return dermatologistRepository.findAllDermatologistsByPharmacy(id);
    }
}
