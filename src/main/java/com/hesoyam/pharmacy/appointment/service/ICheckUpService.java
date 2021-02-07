package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.dto.FreeCheckupDTO;
import com.hesoyam.pharmacy.appointment.exceptions.CheckupNotFoundException;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;
import java.util.List;

public interface ICheckUpService {
    CheckUp createFreeCheckUp(User administrator, FreeCheckupDTO freeCheckupDTO, Long dermatologistId) throws DermatologistNotFoundException, IllegalAccessException;

    List<CheckUp> getUpcomingFreeCheckupsByEmployeeAndPharmacy(Long dermatologistId, String pharmacyId);
    List<CheckUp> getUpcomingFreeCheckupsByPharmacy(Long pharmacyId);
    List<CheckUp> getUpcomingCheckupsByPatient(Long id);

    CheckUp findById(Long id) throws CheckupNotFoundException;
    CheckUp update(CheckUp checkup) throws CheckupNotFoundException;

    CheckUp cancelCheckup(Patient patient, LocalDateTime from, Dermatologist user);
    CheckUp updateCheckupAfterAppointment(Patient patient, LocalDateTime from, String report, Dermatologist dermatologist) throws CheckupNotFoundException;
    List<CheckUp> getAllCheckUpsForPatientAndDermatologist(Patient patient, Dermatologist user);
}
