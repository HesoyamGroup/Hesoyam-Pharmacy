package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.model.Administrator;

import java.util.List;

public interface IAdministratorService {
    List<Administrator> getAdministratorsForPharmacyId(long pharmacyId);
}
