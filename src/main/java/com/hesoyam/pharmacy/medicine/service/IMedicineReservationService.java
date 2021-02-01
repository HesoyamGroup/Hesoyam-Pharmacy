package com.hesoyam.pharmacy.medicine.service;

import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationNotFoundException;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;

import java.util.List;

public interface IMedicineReservationService {
    MedicineReservation getById(Long id) throws MedicineReservationNotFoundException;
    MedicineReservation update(MedicineReservation medicineReservation) throws MedicineReservationNotFoundException;
    List<MedicineReservation> getAll();
    MedicineReservation create(MedicineReservation medicineReservation);
    List<MedicineReservation> getByPatientId(Long id);
    MedicineReservation getByMedicineReservationCode(String code) throws MedicineReservationNotFoundException;
    int getPickedupReservationsCountForPatientId(Long patientId);
}