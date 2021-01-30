package com.hesoyam.pharmacy.medicine.service.impl;

import com.hesoyam.pharmacy.medicine.model.Contraindication;
import com.hesoyam.pharmacy.medicine.repository.ContraindicationRepository;
import com.hesoyam.pharmacy.medicine.service.IContraindicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContraindicationService implements IContraindicationService {
    @Autowired
    private ContraindicationRepository contraindicationRepository;

    @Override
    public List<Contraindication> getAll() {
        return contraindicationRepository.findAll();
    }
}
