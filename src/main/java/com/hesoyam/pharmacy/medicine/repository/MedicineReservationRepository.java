package com.hesoyam.pharmacy.medicine.repository;

import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationStatus;
import com.hesoyam.pharmacy.user.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineReservationRepository extends JpaRepository<MedicineReservation, Long> {
    List<MedicineReservation> findByPatient_Id(Long id);
    MedicineReservation findByCode(String code);
    int countMedicineReservationsByPatient_IdAndAndMedicineReservationStatus(Long patientId, MedicineReservationStatus medicineReservationStatus);
}
