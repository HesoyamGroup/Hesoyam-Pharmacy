package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.DTO.PharmacyCreateDTO;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import java.util.List;

public interface IPharmacyService {
    Pharmacy create(PharmacyCreateDTO pharmacyCreateDTO);
    List<Pharmacy> getAll();
    List<Pharmacy> getAllByAdministrator(Long id);
}
