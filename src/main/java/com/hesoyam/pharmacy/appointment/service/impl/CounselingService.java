package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.exceptions.CounselingNotFoundException;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.repository.CounselingRepository;
import com.hesoyam.pharmacy.appointment.service.ICounselingService;
import com.hesoyam.pharmacy.user.model.LoyaltyAccount;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.service.ILoyaltyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private List<Counseling> filterByDate(List<Counseling> allCounselings) {
        List<Counseling> filtered = new ArrayList<>();
        for(Counseling counseling : allCounselings){
            if(counseling.getDateTimeRange().getFrom().isAfter(LocalDateTime.now())){
                filtered.add(counseling);
            }
        }
        return filtered;
    }
}
