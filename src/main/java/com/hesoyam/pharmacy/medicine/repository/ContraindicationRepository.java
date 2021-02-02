package com.hesoyam.pharmacy.medicine.repository;

import com.hesoyam.pharmacy.medicine.model.Contraindication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContraindicationRepository extends JpaRepository<Contraindication, Long> {
}
