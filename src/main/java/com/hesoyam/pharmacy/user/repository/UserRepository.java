package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
