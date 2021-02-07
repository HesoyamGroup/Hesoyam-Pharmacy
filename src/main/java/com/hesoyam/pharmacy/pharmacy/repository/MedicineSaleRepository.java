package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.MedicineSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineSaleRepository extends JpaRepository<MedicineSale, Long> {

    List<MedicineSale> getAllByPharmacy_Id(Long pharmacyId);
}
