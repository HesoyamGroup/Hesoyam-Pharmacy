package com.hesoyam.pharmacy.medicine.repository;

import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineReservationItemRepository extends JpaRepository<MedicineReservationItem, Long> {
}
