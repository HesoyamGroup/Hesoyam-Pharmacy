package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
