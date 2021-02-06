package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.exceptions.CounselingNotFoundException;
import com.hesoyam.pharmacy.appointment.model.Counseling;

import java.util.List;

public interface ICounselingService {

    List<Counseling> getAllFreeCounselings();
    List<Counseling> getFreeCounselingsByPharmacyId(Long id);
    Counseling findById(Long id) throws CounselingNotFoundException;
    Counseling update(Counseling counseling) throws  CounselingNotFoundException;
}
