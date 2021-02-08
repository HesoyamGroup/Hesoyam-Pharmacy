package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.exceptions.CounselingNotFoundException;
import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.repository.CounselingRepository;
import com.hesoyam.pharmacy.appointment.service.ICounselingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounselingService implements ICounselingService {

    @Autowired
    private CounselingRepository counselingRepository;

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
    public List<Counseling> getAllCompletedCounselingsByPatient(Long id) {
        List<Counseling> counselings = counselingRepository.getAllByPatient_IdAndAppointmentStatus(id, AppointmentStatus.COMPLETED);

        return counselings;
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

    private List<Counseling> getUpcomingCounselings(List<Counseling> counselings){
        return counselings.stream().filter(Appointment::isUpcoming).collect(Collectors.toList());
    }
}
