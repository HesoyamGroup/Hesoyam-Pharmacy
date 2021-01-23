package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String name);
}
