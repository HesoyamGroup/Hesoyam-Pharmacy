package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.model.Dermatologist;

import java.util.List;

public interface IDermatologistService {
    List<Dermatologist> getWorkingDermatologistsAtPharmacy(Long id);
}
