package com.hesoyam.pharmacy.medicine.service;

import com.hesoyam.pharmacy.medicine.model.MedicineReservation;

import java.util.List;

public interface IMedicineReservationService {
    List<MedicineReservation> getAll();
    MedicineReservation create(MedicineReservation medicineReservation);
    List<MedicineReservation> getByPatientId(Long id);
    MedicineReservation getByMedicineReservationCode(String code);
}
