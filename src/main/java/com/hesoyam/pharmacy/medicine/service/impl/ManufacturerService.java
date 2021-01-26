package com.hesoyam.pharmacy.medicine.service.impl;

import com.hesoyam.pharmacy.medicine.model.Manufacturer;
import com.hesoyam.pharmacy.medicine.repository.ManufacterRepository;
import com.hesoyam.pharmacy.medicine.service.IManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService implements IManufacturerService {
    @Autowired
    private ManufacterRepository manufacterRepository;

    @Override
    public List<Manufacturer> getAll() {
        return manufacterRepository.findAll();
    }

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        manufacturer.setId(null);
        return manufacterRepository.save(manufacturer);
    }
}
