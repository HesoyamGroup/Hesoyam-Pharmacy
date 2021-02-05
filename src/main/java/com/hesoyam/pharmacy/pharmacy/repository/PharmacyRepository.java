package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    @Query("select p from Pharmacy p join p.inventory i join i.inventoryItems it join it.medicine m where m.id = :medicine_id and it.available>0")
    List<Pharmacy> getPharmacyByMedicineAvailability(@Param("medicine_id") Long medicineId);

    @Query("select p from Pharmacy p join p.administrator a where a.id = :administrator_id")
    Pharmacy getPharmacyByAdministrator(@Param("administrator_id") Long administratorId);

    @Query("SELECT pharmacy " +
            "FROM Pharmacy pharmacy join pharmacy.inventory inventory join inventory.inventoryItems invItems join invItems.medicine medicine " +
            "WHERE medicine.id IN (:medicineIds) " +
            "GROUP BY pharmacy.id " +
            "HAVING COUNT(pharmacy) = :listSize")
    List<Pharmacy> getPharmaciesByMedicineAvailability(@Param("medicineIds")List<Long> medicineIds, @Param("listSize") Long size);
    @Query("SELECT ( count(pharmacy) > 0 ) " +
            "FROM Pharmacy pharmacy join pharmacy.inventory inventory join inventory.inventoryItems invItems " +
            "WHERE pharmacy.id = :pharmacyId AND invItems.medicine.id = :medicineId AND invItems.available - invItems.reserved >= :quantity ")
    Boolean canPharmacyOfferMedicineQuantity(@Param("pharmacyId") Long pharmacyId, @Param("medicineId") Long medicineId, @Param("quantity") int quantity);
}
