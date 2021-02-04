package com.hesoyam.pharmacy.employee_management.repository;

import com.hesoyam.pharmacy.employee_management.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    @Query("select s from Employee e join e.shifts s where e.id = :employee_id and s.type = 'WORK'")
    List<Shift> getAllByEmployee(@Param("employee_id") Long employeeId);
}
