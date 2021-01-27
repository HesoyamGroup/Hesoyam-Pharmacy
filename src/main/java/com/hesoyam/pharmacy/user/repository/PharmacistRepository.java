package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

    List<Pharmacist> findAllByPharmacy_Id(Long id);
}
