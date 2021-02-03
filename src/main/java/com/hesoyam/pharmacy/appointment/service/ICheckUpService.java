package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.dto.FreeCheckupDTO;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.model.User;

import java.util.List;

public interface ICheckUpService {
    CheckUp createFreeCheckUp(User administrator, FreeCheckupDTO freeCheckupDTO, Long dermatologistId) throws DermatologistNotFoundException, IllegalAccessException;

    List<CheckUp> getUpcomingFreeCheckupsByEmployeeAndPharmacy(Long dermatologistId, String pharmacyId);
}
