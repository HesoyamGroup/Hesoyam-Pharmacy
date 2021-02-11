package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.dto.CounselingIDDTO;
import com.hesoyam.pharmacy.appointment.exceptions.CounselingNotFoundException;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ICounselingService {
    Counseling updateCounselingAfterAppointment(long patientId, LocalDateTime from, String report, Pharmacist pharmacist) throws CounselingNotFoundException;

    List<Counseling> getAllCounselingsForPatientAndPharmacist(Patient patient, Pharmacist user);

    Counseling cancelCounseling(Patient patient, LocalDateTime from, Pharmacist pharmacist);

    List<Counseling> getAllFreeCounselings();
    List<Counseling> getFreeCounselingsByPharmacyId(Long id);
    List<Counseling> getUpcomingCounselingsByPatient(Long id);
    List<Counseling> getAllCompletedCounselingsByPatient(Long id);

    Counseling findById(Long id) throws CounselingNotFoundException;
    Counseling update(Counseling counseling) throws  CounselingNotFoundException;
    CounselingIDDTO reserve(CounselingIDDTO counselingIDDTO, User user) throws CounselingNotFoundException, PatientNotFoundException;

}
