package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.dto.LoyaltyAccountMembershipDTO;
import com.hesoyam.pharmacy.user.dto.LoyaltyProgramConfigUpdateDTO;
import com.hesoyam.pharmacy.user.exceptions.LoyaltyAccountMembershipInvalidUpdateException;
import com.hesoyam.pharmacy.user.model.*;

import java.util.List;

public interface ILoyaltyAccountService {
    List<LoyaltyAccountMembership> getAllLoyaltyAccountMemberships();
    List<LoyaltyProgramConfig> getAllLoyaltyProgramConfig();
    LoyaltyProgramConfig updateLoyaltyProgramConfig(LoyaltyProgramConfigUpdateDTO loyaltyProgramConfigUpdateDTO);
    LoyaltyAccountMembership updateLoyaltyAccountMembership(LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO) throws LoyaltyAccountMembershipInvalidUpdateException;
    LoyaltyAccountMembership createLoyaltyAccountMembership(LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO) throws LoyaltyAccountMembershipInvalidUpdateException;
    double calculateDiscountedPrice(Patient patient, double currentPrice);
    LoyaltyAccount createDefaultLoyaltyAccount(Patient patient);
    LoyaltyAccount getPatientLoyaltyAccount(Patient patient);
    LoyaltyAccount update(LoyaltyAccount loyaltyAccount);
    int penalizeForMissingAppointment(Patient patient);
}
