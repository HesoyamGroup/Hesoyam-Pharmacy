package com.hesoyam.pharmacy.medicine.repository;

import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;

public interface MedicineReservationRepository extends JpaRepository<MedicineReservation, Long> {
    List<MedicineReservation> findByPatient_Id(Long id);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    MedicineReservation findByCode(String code);

    MedicineReservation findByCodeAndPharmacy_Id(String code, long id);
    int countMedicineReservationsByPatient_IdAndAndMedicineReservationStatusAndPharmacy_Id(Long patientId, MedicineReservationStatus medicineReservationStatus, Long pharmacyId);
    List<MedicineReservation> findByMedicineReservationStatus(MedicineReservationStatus medicineReservationStatus);

}
