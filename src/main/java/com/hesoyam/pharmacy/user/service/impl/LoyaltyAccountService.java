package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.dto.LoyaltyAccountMembershipDTO;
import com.hesoyam.pharmacy.user.dto.LoyaltyProgramConfigUpdateDTO;
import com.hesoyam.pharmacy.user.exceptions.LoyaltyAccountMembershipInvalidDeleteRequest;
import com.hesoyam.pharmacy.user.exceptions.LoyaltyAccountMembershipInvalidUpdateException;
import com.hesoyam.pharmacy.user.model.LoyaltyAccount;
import com.hesoyam.pharmacy.user.model.LoyaltyAccountMembership;
import com.hesoyam.pharmacy.user.model.LoyaltyProgramConfig;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.repository.LoyaltyAccountMembershipRepository;
import com.hesoyam.pharmacy.user.repository.LoyaltyAccountRepository;
import com.hesoyam.pharmacy.user.repository.LoyaltyProgramConfigRepository;
import com.hesoyam.pharmacy.user.service.ILoyaltyAccountService;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LoyaltyAccountService implements ILoyaltyAccountService {

    @Autowired
    private LoyaltyAccountMembershipRepository loyaltyAccountMembershipRepository;

    @Autowired
    private LoyaltyProgramConfigRepository loyaltyProgramConfigRepository;

    @Autowired
    private LoyaltyAccountRepository loyaltyAccountRepository;


    @Override
    public List<LoyaltyAccountMembership> getAllLoyaltyAccountMemberships() {
        return loyaltyAccountMembershipRepository.findAll();
    }

    @Override
    public List<LoyaltyProgramConfig> getAllLoyaltyProgramConfig() {
        return loyaltyProgramConfigRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public LoyaltyProgramConfig updateLoyaltyProgramConfig(LoyaltyProgramConfigUpdateDTO loyaltyProgramConfigUpdateDTO) {
        LoyaltyProgramConfig dbConfig = loyaltyProgramConfigRepository.getOne(loyaltyProgramConfigUpdateDTO.getId());
        dbConfig.setCounselingPoints(loyaltyProgramConfigUpdateDTO.getCounselingPoints());
        dbConfig.setCheckUpPoints(loyaltyProgramConfigUpdateDTO.getCheckUpPoints());
        return loyaltyProgramConfigRepository.save(dbConfig);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Retryable(value = {CannotAcquireLockException.class},maxAttempts = 3, backoff = @Backoff(750))
    public LoyaltyAccountMembership updateLoyaltyAccountMembership(LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO) throws LoyaltyAccountMembershipInvalidUpdateException {
        validateLoyaltyAccountMembership(loyaltyAccountMembershipDTO);
        LoyaltyAccountMembership loyaltyAccountMembership = loyaltyAccountMembershipRepository.getOne(loyaltyAccountMembershipDTO.getId());
        loadLoyaltyAccountMembershipWithDTOData(loyaltyAccountMembership, loyaltyAccountMembershipDTO);
        try{
            loyaltyAccountMembership = loyaltyAccountMembershipRepository.save(loyaltyAccountMembership);
            refreshLoyaltyAccounts();
            return loyaltyAccountMembership;

        }catch (DataIntegrityViolationException e){
            throw new LoyaltyAccountMembershipInvalidUpdateException("Loyalty membership name must be unique.");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public LoyaltyAccountMembership createLoyaltyAccountMembership(LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO) throws LoyaltyAccountMembershipInvalidUpdateException {
        loyaltyAccountMembershipDTO.setId(-1l);
        validateLoyaltyAccountMembership(loyaltyAccountMembershipDTO);
        LoyaltyAccountMembership loyaltyAccountMembership = new LoyaltyAccountMembership();
        loadLoyaltyAccountMembershipWithDTOData(loyaltyAccountMembership, loyaltyAccountMembershipDTO);
        try{
            loyaltyAccountMembership = loyaltyAccountMembershipRepository.save(loyaltyAccountMembership);
            refreshLoyaltyAccounts();
            return loyaltyAccountMembership;
        }catch (DataIntegrityViolationException e){
            throw new LoyaltyAccountMembershipInvalidUpdateException("Loyalty membership name must be unique.");
        }
    }


    @Override
    public double calculateDiscountedPrice(Patient patient, double currentPrice) {
        LoyaltyAccount loyaltyAccount = loyaltyAccountRepository.getByPatientId(patient.getId());
        return loyaltyAccount.getDiscountedPrice(currentPrice);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public LoyaltyAccount createDefaultLoyaltyAccount(Patient patient) {
        LoyaltyAccountMembership loyaltyAccountMembership = loyaltyAccountMembershipRepository.findLoyaltyAccountMembershipByMinPoints(0);
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount(0, patient, loyaltyAccountMembership);
        return loyaltyAccountRepository.save(loyaltyAccount);
    }

    @Override
    public LoyaltyAccount getPatientLoyaltyAccount(Patient patient) {
        return loyaltyAccountRepository.getByPatientId(patient.getId());
    }

    @Override
    public LoyaltyAccount update(LoyaltyAccount loyaltyAccount) {
        return loyaltyAccountRepository.save(loyaltyAccount);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteLoyaltyAccountMembership(Long loyaltyAccountMembershipId) {
        LoyaltyAccountMembership loyaltyAccountMembership = loyaltyAccountMembershipRepository.getOne(loyaltyAccountMembershipId);
        try{
            loyaltyAccountRepository.setMembershipToNullWhereMembership(loyaltyAccountMembership);
            loyaltyAccountMembershipRepository.delete(loyaltyAccountMembership);
            refreshLoyaltyAccounts();
        }catch (DataIntegrityViolationException e){
            throw new LoyaltyAccountMembershipInvalidDeleteRequest("There are people in that group!");
        }
    }

    @Override
    public void refreshLoyaltyAccounts() {
        loyaltyAccountRepository.refreshLoyaltyAccounts();
    }

    @Override
    public LoyaltyAccount getPatientLoyaltyAccountByPatientId(Long id) {
        return loyaltyAccountRepository.getByPatientId(id);
    }


    private LoyaltyAccountMembership loadLoyaltyAccountMembershipWithDTOData(LoyaltyAccountMembership loyaltyAccountMembership, LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO){
        loyaltyAccountMembership.setLoyaltyProgramConfig(loyaltyAccountMembershipDTO.getLoyaltyProgramConfig());
        loyaltyAccountMembership.setDiscount(loyaltyAccountMembershipDTO.getDiscount());
        loyaltyAccountMembership.setMinPoints(loyaltyAccountMembershipDTO.getMinPoints());
        loyaltyAccountMembership.setName(loyaltyAccountMembershipDTO.getName());
        return loyaltyAccountMembership;
    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void validateLoyaltyAccountMembership(LoyaltyAccountMembershipDTO loyaltyAccountMembershipDTO) throws LoyaltyAccountMembershipInvalidUpdateException {
        int membershipsWithSameMinPointsCount = loyaltyAccountMembershipRepository.countAllByMinPointsAndIdIsNot(loyaltyAccountMembershipDTO.getMinPoints(), loyaltyAccountMembershipDTO.getId());
        if(membershipsWithSameMinPointsCount != 0) throw new LoyaltyAccountMembershipInvalidUpdateException("Memberships with same min points value are not allowed.");
    }


}
