package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.DTO.PharmacyCreateDTO;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.repository.PharmacyRepository;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PharmacyService implements IPharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public Pharmacy create(PharmacyCreateDTO pharmacyCreateDTO) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(pharmacyCreateDTO.getName());
        pharmacy.setAddress(pharmacyCreateDTO.getAddress());
        pharmacy.setDescription(pharmacyCreateDTO.getDescription());

        Pharmacy savedPharmacy = pharmacyRepository.save(pharmacy);
        return savedPharmacy;
    }

    @Override
    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public List<Pharmacy> getAllByAdministrator(Long id) {
        return null;
    }

    @Override
    public Pharmacy findOne(Long id) {
        return pharmacyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Pharmacy> findAllPharmaciesByMedicine(Long id) {
        return pharmacyRepository.getPharmacyByMedicineAvailability(id);
    }
}
