package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.exceptions.PharmacistNotFoundException;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Pharmacist;

import java.util.List;

public interface IPharmacistService {
    Pharmacist getById(Long id) throws PharmacistNotFoundException;
    List<Pharmacist> getWorkingPharmacistsAtPharmacy(Long id);
}
