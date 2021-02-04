package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.dto.FreeCheckupDTO;
import com.hesoyam.pharmacy.appointment.exceptions.CheckupNotFoundException;
import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.repository.CheckUpRepository;
import com.hesoyam.pharmacy.appointment.service.ICheckUpService;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckUpService implements ICheckUpService {

    @Autowired
    private CheckUpRepository checkUpRepository;

    @Autowired
    private IDermatologistService dermatologistService;

    @Autowired
    private IPharmacyService pharmacyService;

    @Override
    public CheckUp createFreeCheckUp(User administrator, FreeCheckupDTO freeCheckupDTO, Long dermatologistId) throws DermatologistNotFoundException, IllegalAccessException {
        Pharmacy pharmacy = pharmacyService.getByAdministrator(administrator.getId());
        Dermatologist dermatologist = dermatologistService.getById(dermatologistId);

        if(!dermatologist.isAdministratorMyBoss(administrator)){
            throw new IllegalAccessException();
        }

        CheckUp newCheckUp = new CheckUp();
        newCheckUp.setDermatologist(dermatologist);
        newCheckUp.setAppointmentStatus(AppointmentStatus.FREE);
        newCheckUp.setDateTimeRange(freeCheckupDTO.getRange());
        newCheckUp.setPharmacy(pharmacy);
        newCheckUp.setPrice(freeCheckupDTO.getPrice());

        boolean available = dermatologist.isAvailable(newCheckUp.getDateTimeRange(), pharmacy);
        return available ? checkUpRepository.save(newCheckUp) : null;
    }

    @Override
    public List<CheckUp> getUpcomingFreeCheckupsByEmployeeAndPharmacy(Long dermatologistId, String pharmacyId) {
        List<CheckUp> checkUps;

        if(pharmacyId == null || pharmacyId.isEmpty())
            checkUps = checkUpRepository.getAllByDermatologist_Id(dermatologistId);
        else
            checkUps = checkUpRepository.getAllByDermatologist_IdAndPharmacy_IdAndAppointmentStatus(dermatologistId, Long.parseLong(pharmacyId), AppointmentStatus.FREE);
        return getUpcomingCheckUps(checkUps);
    }

    @Override
    public List<CheckUp> getUpcomingFreeCheckupsByPharmacy(Long pharmacyId) {
        List<CheckUp> checkUps;
        checkUps = checkUpRepository.getAllByPharmacy_Id(pharmacyId);

        return getUpcomingCheckUps(checkUps);
    }

    @Override
    public CheckUp findById(Long id) throws CheckupNotFoundException {
        return checkUpRepository.findById(id).orElseThrow(() -> new CheckupNotFoundException(id));
    }

    @Override
    public CheckUp update(CheckUp checkupData) throws CheckupNotFoundException {
        CheckUp checkUp = checkUpRepository.getOne(checkupData.getId());
        if(checkUp == null) throw new CheckupNotFoundException(checkUp.getId());

        checkUp.update(checkupData);
        checkUp = checkUpRepository.save(checkUp);

        return checkUp;
    }

    private List<CheckUp> getUpcomingCheckUps(List<CheckUp> checkUps) {
        return checkUps.stream().filter(Appointment::isUpcoming).collect(Collectors.toList());
    }
}
