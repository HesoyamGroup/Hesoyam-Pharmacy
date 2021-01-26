package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
