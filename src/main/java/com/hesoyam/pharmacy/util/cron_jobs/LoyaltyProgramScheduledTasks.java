package com.hesoyam.pharmacy.util.cron_jobs;

import com.hesoyam.pharmacy.user.service.ILoyaltyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Component
public class LoyaltyProgramScheduledTasks {

    @Autowired
    private ILoyaltyAccountService loyaltyAccountService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Scheduled(cron = "0 0 */6 * * *")
    public void updateAccountMemberships() {
        loyaltyAccountService.refreshLoyaltyAccounts();
    }
}