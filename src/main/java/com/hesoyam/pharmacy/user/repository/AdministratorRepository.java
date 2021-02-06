package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    List<Administrator> findAllByPharmacy_Id(long pharmacyId);
}
