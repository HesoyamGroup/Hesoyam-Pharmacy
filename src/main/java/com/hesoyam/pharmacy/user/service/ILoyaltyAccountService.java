package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.dto.LoyaltyAccountMembershipDTO;
import com.hesoyam.pharmacy.user.dto.LoyaltyProgramConfigUpdateDTO;
import com.hesoyam.pharmacy.user.exceptions.LoyaltyAccountMembershipInvalidUpdateException;
import com.hesoyam.pharmacy.user.model.LoyaltyAccountMembership;
import com.hesoyam.pharmacy.user.model.LoyaltyProgramConfig;

import java.util.List;

public interface ILoyaltyAccountService {
    List<LoyaltyAccountMembership> getAllLoyaltyAccountMemberships();
    List<LoyaltyProgramConfig> getAllLoyaltyProgramConfig();
    LoyaltyProgramConfig updateLoyaltyProgramConfig(LoyaltyProgramConfigUpdateDTO loyaltyProgramConfigUpdateDTO);
    LoyaltyAccountMembership updateLoyaltyAccountMembership(LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO) throws LoyaltyAccountMembershipInvalidUpdateException;
    LoyaltyAccountMembership createLoyaltyAccountMembership(LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO) throws LoyaltyAccountMembershipInvalidUpdateException;
}
