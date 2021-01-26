package com.hesoyam.pharmacy.medicine.repository;

import com.hesoyam.pharmacy.medicine.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacterRepository extends JpaRepository<Manufacturer, Long> {
}
