package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import java.util.List;

public interface IPharmacyService {
    List<Pharmacy> getAll();
    List<Pharmacy> getAllByAdministrator(Long id);
    Pharmacy findOne(Long id);
}
