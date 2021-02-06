package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.exceptions.CounselingNotFoundException;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.repository.CounselingRepository;
import com.hesoyam.pharmacy.appointment.service.ICounselingService;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CounselingService implements ICounselingService {

    @Autowired
    private CounselingRepository counselingRepository;

    @Override
    public void updateCounselingAfterAppointment(long patientId, LocalDateTime from, String report, Pharmacist pharmacist)
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
    }

    @Override
    public List<Counseling> getAllCounselingsForPatientAndPharmacist(Patient patient, Pharmacist user) {
        List<Counseling> allCounselings = counselingRepository.findAllByPatientAndPharmacist(patient, user);
        allCounselings = filterByDate(allCounselings);
        return allCounselings;
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
