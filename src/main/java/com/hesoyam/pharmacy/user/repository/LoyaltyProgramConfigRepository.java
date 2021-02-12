package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.LoyaltyProgramConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface LoyaltyProgramConfigRepository extends JpaRepository<LoyaltyProgramConfig, Long> {
    @Override
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    LoyaltyProgramConfig getOne(Long id);
}
