package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
    List<Pharmacist> findAllByPharmacy_Id(Long id);

    @Query("select p from Pharmacist p join p.pharmacy ph join ph.administrator a where a.id = :administrator_id")
    List<Pharmacist> findAllByPharmacyAdministrator(@Param("administrator_id") Long administratorId);

    List<Pharmacist> findAllByFirstNameLikeAndLastNameLike(String firstName, String lastName);
}
