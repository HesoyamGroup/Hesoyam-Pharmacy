package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.dto.LoyaltyAccountMembershipDTO;
import com.hesoyam.pharmacy.user.dto.LoyaltyProgramConfigUpdateDTO;
import com.hesoyam.pharmacy.user.exceptions.LoyaltyAccountMembershipInvalidUpdateException;
import com.hesoyam.pharmacy.user.model.LoyaltyAccountMembership;
import com.hesoyam.pharmacy.user.model.LoyaltyProgramConfig;
import com.hesoyam.pharmacy.user.repository.LoyaltyAccountMembershipRepository;
import com.hesoyam.pharmacy.user.repository.LoyaltyProgramConfigRepository;
import com.hesoyam.pharmacy.user.service.ILoyaltyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoyaltyAccountService implements ILoyaltyAccountService {

    @Autowired
    private LoyaltyAccountMembershipRepository loyaltyAccountMembershipRepository;

    @Autowired
    private LoyaltyProgramConfigRepository loyaltyProgramConfigRepository;
    @Override
    public List<LoyaltyAccountMembership> getAllLoyaltyAccountMemberships() {
        return loyaltyAccountMembershipRepository.findAll();
    }

    @Override
    public List<LoyaltyProgramConfig> getAllLoyaltyProgramConfig() {
        return loyaltyProgramConfigRepository.findAll();
    }

    @Override
    public LoyaltyProgramConfig updateLoyaltyProgramConfig(LoyaltyProgramConfigUpdateDTO loyaltyProgramConfigUpdateDTO) {
        LoyaltyProgramConfig dbConfig = loyaltyProgramConfigRepository.getOne(loyaltyProgramConfigUpdateDTO.getId());
        dbConfig.setCounselingPoints(loyaltyProgramConfigUpdateDTO.getCounselingPoints());
        dbConfig.setCheckUpPoints(loyaltyProgramConfigUpdateDTO.getCheckUpPoints());

        return loyaltyProgramConfigRepository.save(dbConfig);
    }

    @Override
    public LoyaltyAccountMembership updateLoyaltyAccountMembership(LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO) throws LoyaltyAccountMembershipInvalidUpdateException {
        validateLoyaltyAccountMembership(loyaltyAccountMembershipDTO);
        LoyaltyAccountMembership loyaltyAccountMembership = loyaltyAccountMembershipRepository.getOne(loyaltyAccountMembershipDTO.getId());
        loadLoyaltyAccountMembershipWithDTOData(loyaltyAccountMembership, loyaltyAccountMembershipDTO);
        try{
            return loyaltyAccountMembershipRepository.save(loyaltyAccountMembership);
        }catch (DataIntegrityViolationException e){
            throw new LoyaltyAccountMembershipInvalidUpdateException("Loyalty membership name must be unique.");
        }
    }

    @Override
    public LoyaltyAccountMembership createLoyaltyAccountMembership(LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO) throws LoyaltyAccountMembershipInvalidUpdateException {
        loyaltyAccountMembershipDTO.setId(-1l);
        validateLoyaltyAccountMembership(loyaltyAccountMembershipDTO);
        LoyaltyAccountMembership loyaltyAccountMembership = new LoyaltyAccountMembership();
        loadLoyaltyAccountMembershipWithDTOData(loyaltyAccountMembership, loyaltyAccountMembershipDTO);
        try{
            return loyaltyAccountMembershipRepository.save(loyaltyAccountMembership);
        }catch (DataIntegrityViolationException e){
            throw new LoyaltyAccountMembershipInvalidUpdateException("Loyalty membership name must be unique.");
        }
    }

    private LoyaltyAccountMembership loadLoyaltyAccountMembershipWithDTOData(LoyaltyAccountMembership loyaltyAccountMembership, LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO){
        loyaltyAccountMembership.setLoyaltyProgramConfig(loyaltyAccountMembershipDTO.getLoyaltyProgramConfig());
        loyaltyAccountMembership.setDiscount(loyaltyAccountMembershipDTO.getDiscount());
        loyaltyAccountMembership.setMinPoints(loyaltyAccountMembershipDTO.getMinPoints());
        loyaltyAccountMembership.setName(loyaltyAccountMembershipDTO.getName());
        return loyaltyAccountMembership;
    }



    private void validateLoyaltyAccountMembership(LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO) throws LoyaltyAccountMembershipInvalidUpdateException {
        int membershipsWithSameMinPointsCount = loyaltyAccountMembershipRepository.countAllByMinPointsAndIdIsNot(loyaltyAccountMembershipDTO.getMinPoints(), loyaltyAccountMembershipDTO.getId());
        if(membershipsWithSameMinPointsCount != 0) throw new LoyaltyAccountMembershipInvalidUpdateException("Memberships with same min points value are not allowed.");
    }


}