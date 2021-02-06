package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.LoyaltyAccountMembership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyAccountMembershipRepository extends JpaRepository<LoyaltyAccountMembership, Long> {
    int countAllByMinPointsAndIdIsNot(Integer minPoints, Long id);
    LoyaltyAccountMembership findLoyaltyAccountMembershipByMinPoints(Integer minPoints);
}
