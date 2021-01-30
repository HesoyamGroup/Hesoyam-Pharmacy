package com.hesoyam.pharmacy.medicine.service.impl;

import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.repository.MedicineReservationRepository;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineReservationService implements IMedicineReservationService {

    @Autowired
    MedicineReservationRepository medicineReservationRepository;

    @Override
    public List<MedicineReservation> getAll() {
        return medicineReservationRepository.findAll();
    }

    @Override
    public MedicineReservation create(MedicineReservation medicineReservation) {
        medicineReservation.setId(null);//Since we don't use DTO, we want to make sure we are not updating a random medicine.
        return medicineReservationRepository.save(medicineReservation);
    }

    @Override
    public List<MedicineReservation> getByPatientId(Long id) {
        return medicineReservationRepository.findByPatient_Id(id);
    }

    @Override
    public MedicineReservation getByMedicineReservationCode(String code) {
        return medicineReservationRepository.findByCode(code);
    }
}
