package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.dto.FreeCheckupDTO;
import com.hesoyam.pharmacy.appointment.events.OnCheckupReservationCompletedEvent;
import com.hesoyam.pharmacy.appointment.exceptions.CheckupNotFoundException;
import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.repository.CheckUpRepository;
import com.hesoyam.pharmacy.appointment.service.ICheckUpService;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserPenalizedException;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IDermatologistService;
import com.hesoyam.pharmacy.user.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CheckUpService implements ICheckUpService {

    @Autowired
    private CheckUpRepository checkUpRepository;

    @Autowired
    private IDermatologistService dermatologistService;

    @Autowired
    private IPharmacyService pharmacyService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private IPatientService patientService;


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
        List<CheckUp> checkUps = checkUpRepository.getAllByPharmacy_IdAndAppointmentStatus(pharmacyId, AppointmentStatus.FREE);

        return getUpcomingCheckUps(checkUps);
    }

    @Override
    public List<CheckUp> getUpcomingCheckupsByPatient(Long id) {
        List<CheckUp> checkUps = checkUpRepository.getAllByPatient_Id(id);

        return getUpcomingCheckUps(checkUps);
    }

    @Override
    public List<CheckUp> getAllCompletedCheckupsByPatient(Long id) {
        List<CheckUp> checkUps = checkUpRepository.getAllByPatient_IdAndAppointmentStatus(id, AppointmentStatus.COMPLETED);

        return checkUps;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CheckUp findById(Long id) throws CheckupNotFoundException {
        return checkUpRepository.findById(id).orElseThrow(() -> new CheckupNotFoundException(id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CheckUp update(CheckUp checkupData) throws CheckupNotFoundException {
        CheckUp checkUp = checkUpRepository.getOne(checkupData.getId());
        if(checkUp == null) throw new CheckupNotFoundException(checkupData.getId());

        checkUp.update(checkupData);
        checkUp = checkUpRepository.save(checkUp);

        return checkUp;
    }

    @Override
    public CheckUp cancelCheckup(Patient patient, LocalDateTime from, Dermatologist user) {
        CheckUp checkup = checkUpRepository.findCheckUpByPatientAndDermatologistAndDateTimeRange_From(patient, user, from);
        checkup.setAppointmentStatus(AppointmentStatus.ABSENT);
        checkUpRepository.save(checkup);
        return checkup;
    }

    private List<CheckUp> getUpcomingCheckUps(List<CheckUp> checkUps) {
        return checkUps.stream().filter(Appointment::isUpcoming).collect(Collectors.toList());
    }

    @Override
    public CheckUp updateCheckupAfterAppointment(Patient patient, LocalDateTime from, String report, Dermatologist dermatologist)
            throws CheckupNotFoundException{
//        CheckUp checkup = checkUpRepository
//                .findCheckUpByPatient_IdAndDermatologist_IdAndDateTimeRange_From(patientId, dermatologist.getId(), from);


        CheckUp checkup = checkUpRepository.findCheckUpByPatientAndDermatologist_EmailAndDateTimeRange_From(patient,
                dermatologist.getEmail(), from);
        if(checkup != null) {
            checkup.setReport(report);
            checkup.setAppointmentStatus(AppointmentStatus.COMPLETED);
            checkUpRepository.save(checkup);
        }
        else {
            throw new CheckupNotFoundException(patient.getId());
        }

        return checkup;
    }

    @Override
    public List<CheckUp> getAllCheckUpsForPatientAndDermatologist(Patient patient, Dermatologist user) {
        List<CheckUp> allCheckUps = checkUpRepository.findAllByPatientAndDermatologist(patient, user);
        allCheckUps = filterByDate(allCheckUps);
        return allCheckUps;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public FreeCheckupDTO reserve(FreeCheckupDTO freeCheckupDTO, User user) throws CheckupNotFoundException, PatientNotFoundException {
        CheckUp checkup = findById(freeCheckupDTO.getId());
        Patient patient = patientService.getById(user.getId());

        if(patient.getPenaltyPoints() >= 3){
            throw new UserPenalizedException(patient.getId());
        }
        if(!checkup.isTakeable()){
            throw new IllegalArgumentException();
        }

        checkup.setAppointmentStatus(AppointmentStatus.TAKEN);
        checkup.setPatient(patient);
        checkup.update(checkup);

        update(checkup);

        applicationEventPublisher.publishEvent(new OnCheckupReservationCompletedEvent(user));
        return freeCheckupDTO;
    }

    private List<CheckUp> filterByDate(List<CheckUp> allCheckUps) {
        List<CheckUp> filtered = new ArrayList<>();
        for (CheckUp checkUp : allCheckUps) {
            if (checkUp.getDateTimeRange().getFrom().isAfter(LocalDateTime.now())) {
                filtered.add(checkUp);
            }
        }
        return filtered;
    }
}
