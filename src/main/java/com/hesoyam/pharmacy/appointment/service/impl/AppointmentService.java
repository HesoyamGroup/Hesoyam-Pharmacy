package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.repository.AppointmentRepository;
import com.hesoyam.pharmacy.appointment.repository.CheckUpRepository;
import com.hesoyam.pharmacy.appointment.repository.CounselingRepository;
import com.hesoyam.pharmacy.appointment.repository.TherapyRepository;
import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import com.hesoyam.pharmacy.user.DTO.PatientDTO;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.util.DateTimeRange;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TherapyRepository therapyRepository;

    @Autowired
    private CheckUpRepository checkUpRepository;

    @Autowired
    private CounselingRepository counselingRepository;


    @Override
    public int getCompletedChecksUpForPatientByDermatologist(Patient patient, Dermatologist dermatologist) {
        return checkUpRepository.countChecksUpByPatientAndAppointmentStatusAndDermatologist(patient, AppointmentStatus.COMPLETED, dermatologist);
    }

    @Override
    public int getCompletedCounselingsForPatientByPharmacist(Patient patient, Pharmacist pharmacist) {
        return counselingRepository.countCounselingsByPatientAndAppointmentStatusAndPharmacist(patient, AppointmentStatus.COMPLETED, pharmacist);
    }

    @Override
    public List<Counseling> getCounselingsForPharmacist(DateTimeRange dateTimeRange, Pharmacist pharmacist) {
        List<Counseling> allCounselings = counselingRepository.findByPharmacist(pharmacist);
        return filterByDateRange(dateTimeRange, allCounselings);
    }

    @Override
    public List<CheckUp> getCheckUpsForDermatologist(DateTimeRange dateTimeRange, Dermatologist dermatologist) {
        List<CheckUp> allCheckups = checkUpRepository.findCheckUpsByDermatologist(dermatologist);
        return filterCheckupsByDateRange(allCheckups, dateTimeRange);
    }


    @Override
    public List<PatientDTO> extractPatientsFromCheckups(Dermatologist dermatologist){
        List<CheckUp> checkUps = checkUpRepository.findCheckUpsByDermatologist(dermatologist);
        List<CheckUp> completedCheckUps = new ArrayList<>();
        checkUps.forEach(checkUp -> {
            if(checkUp.getAppointmentStatus() == AppointmentStatus.COMPLETED)
                completedCheckUps.add(checkUp);
        });
        List<PatientDTO> patients = extractPatients(completedCheckUps);
        return filterUniquePatients(patients);
    }

    private List<PatientDTO> filterUniquePatients(List<PatientDTO> patients) {
        List<PatientDTO> unique = new ArrayList<>();
        for(PatientDTO patient : patients){
            if(!hasPatientWithEmail(unique, patient.getEmail())){
                unique.add(patient);
            } else {
                unique = overwrite(unique, patient);
            }
        }
        return unique;
    }

    private List<PatientDTO> overwrite(List<PatientDTO> unique, PatientDTO patient) {
        List<PatientDTO> overwritten = new ArrayList<>();

        for(PatientDTO test : unique){
            if(test.getEmail().equals(patient.getEmail())){
                overwritten.add(patient);
            } else {
                overwritten.add(test);
            }
        }

        return overwritten;
    }

    private boolean hasPatientWithEmail(List<PatientDTO> unique, String email) {
        for(PatientDTO patient : unique){
            if(patient.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    private List<PatientDTO> extractPatients(List<CheckUp> completedCheckUps) {
        List<PatientDTO> patients = new ArrayList<>();
        completedCheckUps.forEach(checkUp -> patients.add(new PatientDTO(checkUp)));
        return patients;
    }

    private List<CheckUp> filterCheckupsByDateRange(List<CheckUp> allCheckups, DateTimeRange dateTimeRange) {
        List<CheckUp> filtered = new ArrayList<>();
        for(CheckUp checkUp : allCheckups){
            if(isInRange(checkUp.getDateTimeRange(), dateTimeRange))
                filtered.add(checkUp);
        }
        return filtered;
    }

    private List<Counseling> filterByDateRange(DateTimeRange dateTimeRange, List<Counseling> allCounselings) {
        List<Counseling> filtered = new ArrayList<>();
        for(Counseling counseling : allCounselings){
            if(isInRange(counseling.getDateTimeRange(), dateTimeRange))
                filtered.add(counseling);
        }
        return filtered;
    }

    private boolean isInRange(DateTimeRange toCheck, DateTimeRange constraintRange) {
        return isNested(toCheck, constraintRange) || endsInRange(toCheck, constraintRange) ||
                startsInRange(toCheck, constraintRange) || stretchesThroughRange(toCheck, constraintRange);
    }

    private boolean stretchesThroughRange(DateTimeRange toCheck, DateTimeRange constraintRange) {
        return toCheck.getFrom().isBefore(constraintRange.getFrom()) && toCheck.getTo().isBefore(constraintRange.getTo());
    }

    private boolean startsInRange(DateTimeRange toCheck, DateTimeRange constraintRange) {
        return toCheck.getFrom().isAfter(constraintRange.getFrom()) && toCheck.getTo().isAfter(constraintRange.getTo());
    }

    private boolean endsInRange(DateTimeRange toCheck, DateTimeRange constraintRange) {
        return toCheck.getFrom().isBefore(constraintRange.getFrom()) && toCheck.getTo().isBefore(constraintRange.getTo());
    }

    private boolean isNested(DateTimeRange toCheck, DateTimeRange constraintRange) {
        return toCheck.getFrom().isAfter(constraintRange.getFrom()) && toCheck.getTo().isBefore(constraintRange.getTo());
    }

    public int getCompletedAppointmentsCountInPharmacyByPatient(Pharmacy pharmacy, Patient patient) {
        return appointmentRepository.countAppointmentsByPatientAndAppointmentStatusAndPharmacy(patient, AppointmentStatus.COMPLETED, pharmacy);
    }
}
