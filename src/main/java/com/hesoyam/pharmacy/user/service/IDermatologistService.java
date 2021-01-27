package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.model.Dermatologist;

public interface IDermatologistService {
    Dermatologist getById(Long id) throws DermatologistNotFoundException;
}
