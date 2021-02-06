package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.dto.FreeCheckupDTO;
import com.hesoyam.pharmacy.appointment.exceptions.CheckupNotFoundException;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.model.User;
import org.hibernate.annotations.Check;

import java.util.List;

public interface ICheckUpService {
    CheckUp createFreeCheckUp(User administrator, FreeCheckupDTO freeCheckupDTO, Long dermatologistId) throws DermatologistNotFoundException, IllegalAccessException;

    List<CheckUp> getUpcomingFreeCheckupsByEmployeeAndPharmacy(Long dermatologistId, String pharmacyId);
    List<CheckUp> getUpcomingFreeCheckupsByPharmacy(Long pharmacyId);
    List<CheckUp> getUpcomingCheckupsByPatient(Long id);

    CheckUp findById(Long id) throws CheckupNotFoundException;
    CheckUp update(CheckUp checkup) throws CheckupNotFoundException;
}
