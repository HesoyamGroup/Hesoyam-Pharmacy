package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.LoyaltyAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyAccountRepository extends JpaRepository<LoyaltyAccount, Long> {
    LoyaltyAccount getByPatientId(Long id);
}
