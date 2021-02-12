package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.LoyaltyAccountMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface LoyaltyAccountMembershipRepository extends JpaRepository<LoyaltyAccountMembership, Long> {

    int countAllByMinPointsAndIdIsNot(Integer minPoints, Long id);
    LoyaltyAccountMembership findLoyaltyAccountMembershipByMinPoints(Integer minPoints);
}
