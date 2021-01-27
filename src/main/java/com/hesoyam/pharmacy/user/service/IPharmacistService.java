package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Pharmacist;

import java.util.List;

public interface IPharmacistService {
    List<Pharmacist> getWorkingPharmacistsAtPharmacy(Long id);
}
