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
import com.hesoyam.pharmacy.employee_management.model.Shift;
import com.hesoyam.pharmacy.employee_management.model.ShiftType;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.repository.PharmacyRepository;

import com.hesoyam.pharmacy.user.dto.PatientDTO;
import com.hesoyam.pharmacy.user.model.*;
import com.hesoyam.pharmacy.util.DateTimeRange;
import com.hesoyam.pharmacy.util.search.QueryMatchResult;
import com.hesoyam.pharmacy.util.search.UserSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
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

    @Autowired
    private PharmacyRepository pharmacyRepository;


    private static final double MATCHES_FULLY = 1;
    private static final double MATCHES_PARTIALLY = 0.5;


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
    public boolean checkNewAppointment(User user, Patient patient, LocalDateTime range){
        int count = 0;

        if(user.getRoleEnum().equals(RoleEnum.PHARMACIST)) {
            count = counselingRepository.countCounselingsByPharmacistAndDateTimeRange_From((Pharmacist) user,
                    range);
        }
        else
            count = checkUpRepository.countCheckUpsByDermatologistAndDateTimeRange_From((Dermatologist) user,
                    range);

        count += appointmentRepository.countAppointmentsByPatientAndDateTimeRange_From(patient, range);

        if(count > 0 && isInShift((Employee) user, range)){
            return false;
        }

        return true;
    }

    private boolean isInShift(Employee user, LocalDateTime range) {
        List<Shift> shifts = user.getShifts();
        for(Shift shift : shifts){
            if(shift.getType().equals(ShiftType.WORK) && shift.getDateTimeRange().getFrom().isBefore(range)
                    && shift.getDateTimeRange().getTo().isAfter(range)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Appointment createNewAppointment(Patient patient, User employee, long pharmacyId, DateTimeRange range, double price){
        if(checkNewAppointment(employee, patient, range.getFrom())){
            return createAppointmentBasedOnEmployeeType(patient, employee, pharmacyId, range, price);
        }

        return null;
    }

    private Appointment createAppointmentBasedOnEmployeeType(Patient patient, User employee, long pharmacyId, DateTimeRange range, Double price) {
        if(employee.getRoleEnum().equals(RoleEnum.PHARMACIST)){
            return createCounseling(patient, (Pharmacist) employee, pharmacyId, range, price);
        }

        return createCheckUp(patient, (Dermatologist) employee, pharmacyId, range, price);

    }

    private CheckUp createCheckUp(Patient patient, Dermatologist employee, long pharmacyId, DateTimeRange range, Double price) {
        CheckUp appointment = new CheckUp();
        appointment.setAppointmentStatus(AppointmentStatus.TAKEN);
        appointment.setDateTimeRange(range);
        appointment.setReport("");
        appointment.setDermatologist(employee);
        appointment.setPatient(patient);
        appointment.setPharmacy(pharmacyRepository.findById(pharmacyId));
        appointment.setPrice(price);
        return checkUpRepository.save(appointment);
    }

    private Counseling createCounseling(Patient patient, Pharmacist employee, long pharmacyId, DateTimeRange range, Double price) {
        Counseling appointment = new Counseling();
        appointment.setAppointmentStatus(AppointmentStatus.TAKEN);
        appointment.setDateTimeRange(range);
        appointment.setReport("");
        appointment.setPharmacist(employee);
        appointment.setPatient(patient);
        appointment.setPharmacy(pharmacyRepository.findById(pharmacyId));
        appointment.setPrice(price);
        return counselingRepository.save(appointment);
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

    @Override
    public List<PatientDTO> extractPatientsFromCounselings(Pharmacist pharmacist) {
        List<Counseling> counselings = counselingRepository.findByPharmacist(pharmacist);
        List<Counseling> completedCounselings = new ArrayList<>();
        counselings.forEach(counseling -> {
            if(counseling.getAppointmentStatus() == AppointmentStatus.COMPLETED)
                completedCounselings.add(counseling);
        });
        List<PatientDTO> patients = extractCounselingPatients(completedCounselings);
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

    private List<PatientDTO> extractCounselingPatients(List<Counseling> completedCounselings) {
        List<PatientDTO> patients = new ArrayList<>();
        completedCounselings.forEach(counseling -> patients.add(new PatientDTO(counseling)));
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

    @Override
    public List<UserSearchResult> searchUsers(Employee user, String query) {
        List<PatientDTO> patients = new ArrayList<>();
        if(user.getRoleEnum().equals(RoleEnum.PHARMACIST))
            patients = extractPatientsFromCounselings((Pharmacist) user);
        else if(user.getRoleEnum().equals(RoleEnum.DERMATOLOGIST))
            patients = extractPatientsFromCheckups((Dermatologist) user);

        return filterPatientsByQuery(patients, query);
    }

    private List<UserSearchResult> filterPatientsByQuery(List<PatientDTO> patients, String query) {
        List<UserSearchResult> filtered = new ArrayList<>();
        QueryMatchResult currentIterationResult = null;
        for(PatientDTO patient : patients){
            currentIterationResult = matchesQuery(patient, query);
            if(currentIterationResult.getMatches()){
                filtered.add(new UserSearchResult(patient, currentIterationResult.getGrade()));
            }
        }
        return filtered;
    }

    private QueryMatchResult matchesQuery(PatientDTO patient, String query) {
        String normalized = Normalizer.normalize(query, Normalizer.Form.NFD);
        String[] parts = normalized.split(" ");
        String singleEntry = "";
        String firstName = "";
        String lastName = "";

        if(parts.length == 1){
            singleEntry = parts[0];
        }
        else if(parts.length > 1){
            firstName = parts[0];
            lastName = parts[1];
        }
        else{
            return new QueryMatchResult(false);
        }

        if(!singleEntry.isBlank()) {
            if(Normalizer.normalize(patient.getFirstName(), Normalizer.Form.NFD).equalsIgnoreCase(singleEntry.toLowerCase())
                    || Normalizer.normalize(patient.getLastName(), Normalizer.Form.NFD).equalsIgnoreCase(singleEntry.toLowerCase()))
                return new QueryMatchResult(true, MATCHES_FULLY);
            else if(Normalizer.normalize(patient.getFirstName(), Normalizer.Form.NFD)
                    .toLowerCase().startsWith(singleEntry.toLowerCase())
                    || Normalizer.normalize(patient.getLastName(), Normalizer.Form.NFD)
                    .toLowerCase().startsWith(singleEntry.toLowerCase())){
                return new QueryMatchResult(true, MATCHES_PARTIALLY);
            }
            return new QueryMatchResult(false);
        } else {
            if(Normalizer.normalize(patient.getFirstName(), Normalizer.Form.NFD).equalsIgnoreCase(firstName.toLowerCase()) &&
                    Normalizer.normalize(patient.getLastName(), Normalizer.Form.NFD).equalsIgnoreCase(lastName.toLowerCase()))
                return new QueryMatchResult(true, MATCHES_FULLY);
            else if(Normalizer.normalize(patient.getFirstName(), Normalizer.Form.NFD).equalsIgnoreCase(lastName.toLowerCase()) &&
                    Normalizer.normalize(patient.getLastName(), Normalizer.Form.NFD).equalsIgnoreCase(firstName.toLowerCase()))
                return new QueryMatchResult(true, MATCHES_FULLY);
            else if(Normalizer.normalize(patient.getFirstName(), Normalizer.Form.NFD).toLowerCase().startsWith(firstName.toLowerCase()) &&
                    Normalizer.normalize(patient.getLastName(), Normalizer.Form.NFD).toLowerCase().startsWith(lastName.toLowerCase()))
                return new QueryMatchResult(true, MATCHES_PARTIALLY);
            else if(Normalizer.normalize(patient.getFirstName(), Normalizer.Form.NFD).toLowerCase().startsWith(lastName.toLowerCase()) &&
                    Normalizer.normalize(patient.getLastName(), Normalizer.Form.NFD).toLowerCase().startsWith(firstName.toLowerCase()))
                return new QueryMatchResult(true, MATCHES_PARTIALLY);
            else
                return new QueryMatchResult(false);
        }
    }
}
