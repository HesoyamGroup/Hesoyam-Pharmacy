package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
