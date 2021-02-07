package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.LoyaltyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LoyaltyAccountRepository extends JpaRepository<LoyaltyAccount, Long> {
    LoyaltyAccount getByPatientId(Long id);
    @Transactional
    @Modifying
    @Query("UPDATE LoyaltyAccount loy_acc SET loy_acc.loyaltyAccountMembership.id = " +
            "( SELECT lam.id FROM LoyaltyAccountMembership lam " +
            "WHERE lam.minPoints = (SELECT MAX(lam1.minPoints) FROM LoyaltyAccountMembership lam1, LoyaltyAccount loy_acc1 " +
            "WHERE loy_acc.id = loy_acc1.id and loy_acc1.points >= lam1.minPoints))")
    void refreshLoyaltyAccounts();
}