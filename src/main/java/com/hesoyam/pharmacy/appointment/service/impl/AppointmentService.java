package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.repository.AppointmentRepository;
import com.hesoyam.pharmacy.appointment.repository.CheckUpRepository;
import com.hesoyam.pharmacy.appointment.repository.CounselingRepository;
import com.hesoyam.pharmacy.appointment.repository.TherapyRepository;
import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.util.DateTimeRange;
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
        return CounselingRepository.countCounselingsByPatientAndAppointmentStatusAndPharmacist(patient, AppointmentStatus.COMPLETED, pharmacist);
    }

    @Override
    public List<Counseling> getCounselingsForPharmacist(DateTimeRange dateTimeRange, Pharmacist pharmacist) {
        List<Counseling> allCounselings = counselingRepository.findByPharmacist(pharmacist);
        return filterByDateRange(dateTimeRange, allCounselings);
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

}
