package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.dto.DermatologistAddPharmacyDTO;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.User;

import java.util.List;

public interface IDermatologistService {
    Dermatologist getById(Long id) throws DermatologistNotFoundException;
    List<Dermatologist> getWorkingDermatologistsAtPharmacy(Long id);
    List<Dermatologist> getAll(User loggedInUser);
    List<Dermatologist> search(User loggedInUser, String firstName, String lastName);

    List<Dermatologist> getUnsortedDermatologistsByAdministrator(User user);

    Dermatologist addDermatologistToAdministratorPharmacy(User user, DermatologistAddPharmacyDTO dermatologist) throws IllegalAccessException;

    void removeFromPharmacy(Long id, User user) throws IllegalAccessException;
}
