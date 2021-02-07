package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.ServiceSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceSaleRepository extends JpaRepository<ServiceSale, Long> {
    List<ServiceSale> getAllByPharmacy_Id(Long pharmacyId);
}
