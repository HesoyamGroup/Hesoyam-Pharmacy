package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long> {

    @Query("select d from Dermatologist d join d.pharmacies p where p.id = :pharmacy_id")
    List<Dermatologist> findAllDermatologistsByPharmacy(@Param("pharmacy_id") Long pharmacyId);

}
