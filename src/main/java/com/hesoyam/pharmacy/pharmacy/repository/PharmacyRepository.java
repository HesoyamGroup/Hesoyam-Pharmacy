package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    @Query("select p from Pharmacy p join p.inventory i join i.inventoryItems it join it.medicine m where m.id = :medicine_id and it.available-it.reserved>0")
    List<Pharmacy> getPharmacyByMedicineAvailability(@Param("medicine_id") Long medicineId);
}
