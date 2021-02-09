package com.hesoyam.pharmacy.medicine.repository;

import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicineReservationItemRepository extends JpaRepository<MedicineReservationItem, Long> {

    @Query("select mri.medicineReservationItems from MedicineReservation mri where mri.patient.id = :patient_id and mri.medicineReservationStatus = :medicine_reservation_status")
    List<MedicineReservationItem> getMedicineReservationItemsByPatientIdAndReservationStatus(@Param("patient_id") Long id,@Param("medicine_reservation_status") MedicineReservationStatus medicineReservationStatus);

}
