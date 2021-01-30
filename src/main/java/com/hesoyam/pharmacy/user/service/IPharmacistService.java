package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.exceptions.PharmacistNotFoundException;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.model.User;

import java.util.List;

public interface IPharmacistService {
    Pharmacist getById(Long id) throws PharmacistNotFoundException;
    List<Pharmacist> getWorkingPharmacistsAtPharmacy(Long id);
    Pharmacy getPharmacyForPharmacist(Long id);

    List<Pharmacist> getAll(User loggedInUser);

    List<Pharmacist> search(User loggedInUser, String firstName, String lastName);
}
