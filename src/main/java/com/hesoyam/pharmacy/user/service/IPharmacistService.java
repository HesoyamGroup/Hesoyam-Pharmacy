package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.exceptions.PharmacistNotFoundException;
import com.hesoyam.pharmacy.user.model.Pharmacist;

public interface IPharmacistService {
    Pharmacist getById(Long id) throws PharmacistNotFoundException;
}
