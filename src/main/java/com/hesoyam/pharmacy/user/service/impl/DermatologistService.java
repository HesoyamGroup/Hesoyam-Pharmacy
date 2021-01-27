package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.repository.DermatologistRepository;
import com.hesoyam.pharmacy.user.service.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermatologistService implements IDermatologistService {
    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Override
    public List<Dermatologist> getWorkingDermatologistsAtPharmacy(Long id) {
        return dermatologistRepository.findAllDermatologistsByPharmacy(id);
    }
}
