package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.exceptions.CounselingNotFoundException;
import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.repository.CounselingRepository;
import com.hesoyam.pharmacy.appointment.service.ICounselingService;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.service.ILoyaltyAccountService;
import com.hesoyam.pharmacy.util.DateTimeRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounselingService implements ICounselingService {

    @Autowired
    private CounselingRepository counselingRepository;

    @Autowired
    private ILoyaltyAccountService loyaltyAccountService;

    @Override
    public Counseling updateCounselingAfterAppointment(long patientId, LocalDateTime from, String report, Pharmacist pharmacist)
            throws CounselingNotFoundException{
        Counseling counseling = counselingRepository
                .findByPatient_IdAndDateTimeRange_FromAndPharmacist(patientId, from, pharmacist);
        if(counseling != null) {
            counseling.setReport(report);
            counseling.setAppointmentStatus(AppointmentStatus.COMPLETED);
            counselingRepository.save(counseling);
        }
        else {
            throw new CounselingNotFoundException(patientId);
        }

        return counseling;
    }

    @Override
    public List<Counseling> getAllCounselingsForPatientAndPharmacist(Patient patient, Pharmacist user) {
        List<Counseling> allCounselings = counselingRepository.findAllByPatientAndPharmacist(patient, user);
        allCounselings = filterByDate(allCounselings);
        return allCounselings;
    }

    @Override
    public Counseling cancelCounseling(Patient patient, LocalDateTime from, Pharmacist pharmacist) {
        Counseling counseling = counselingRepository.findByPatient_IdAndDateTimeRange_FromAndPharmacist(patient.getId(),
                from, pharmacist);
        counseling.setAppointmentStatus(AppointmentStatus.ABSENT);
        counselingRepository.save(counseling);
        return counseling;
    }

    @Override
    public List<Counseling> getAllFreeCounselings() {
        List<Counseling> counselings = counselingRepository.findByAppointmentStatus(AppointmentStatus.FREE);

        return getUpcomingCounselings(counselings);
    }

    @Override
    public List<Counseling> getFreeCounselingsByPharmacyId(Long id) {
        List<Counseling> counselings = counselingRepository.findByPharmacy_IdAndAppointmentStatus(id, AppointmentStatus.FREE);

        return getUpcomingCounselings(counselings);
    }

    @Override
    public List<Counseling> getUpcomingCounselingsByPatient(Long id) {
        List<Counseling> counselings = counselingRepository.getAllByPatient_Id(id);

        return  getUpcomingCounselings(counselings);
    }

    @Override
    public Counseling findById(Long id) throws CounselingNotFoundException {
        return counselingRepository.findById(id).orElseThrow(() ->new CounselingNotFoundException(id));
    }

    @Override
    public Counseling update(Counseling counselingData) throws CounselingNotFoundException {
        Counseling counseling = counselingRepository.getOne(counselingData.getId());
        if(counseling == null) throw new CounselingNotFoundException(counselingData.getId());

        counseling.update(counselingData);
        counseling = counselingRepository.save(counseling);


        return counseling;
    }


    private List<Counseling> filterByDate(List<Counseling> allCounselings) {
            List<Counseling> filtered = new ArrayList<>();
            for (Counseling counseling : allCounselings) {
                if (counseling.getDateTimeRange().getFrom().isAfter(LocalDateTime.now())) {
                    filtered.add(counseling);
                }
            }
            return filtered;
        }

    private List<Counseling> getUpcomingCounselings(List<Counseling> counselings){
        return counselings.stream().filter(Appointment::isUpcoming).collect(Collectors.toList());

    }
}
