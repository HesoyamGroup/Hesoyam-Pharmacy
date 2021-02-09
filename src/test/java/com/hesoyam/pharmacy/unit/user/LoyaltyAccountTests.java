package com.hesoyam.pharmacy.unit.user;

import com.hesoyam.pharmacy.user.model.LoyaltyAccount;
import com.hesoyam.pharmacy.user.model.LoyaltyAccountMembership;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoyaltyAccountTests {

    @Test
    void calculateLoyaltyAccountDiscountTest(){
        LoyaltyAccount loyaltyAccountWithoutMembership = new LoyaltyAccount();
        LoyaltyAccount loyaltyAccountWithBasicMembership = new LoyaltyAccount();
        LoyaltyAccount loyaltyAccountWithDiscountMembership = new LoyaltyAccount();
        LoyaltyAccount loyaltyAccountWithSignificantDiscountMembership = new LoyaltyAccount();

        LoyaltyAccountMembership basicLoyaltyAccountMembership = new LoyaltyAccountMembership();
        basicLoyaltyAccountMembership.setDiscount(0); //No discount
        LoyaltyAccountMembership discountLoyaltyAccountMembership = new LoyaltyAccountMembership();
        discountLoyaltyAccountMembership.setDiscount(10); //10% discount
        LoyaltyAccountMembership significantDiscountLoyaltyAccountMembership = new LoyaltyAccountMembership();
        significantDiscountLoyaltyAccountMembership.setDiscount(50); //50% discount

        loyaltyAccountWithBasicMembership.setLoyaltyAccountMembership(basicLoyaltyAccountMembership);
        loyaltyAccountWithDiscountMembership.setLoyaltyAccountMembership(discountLoyaltyAccountMembership);
        loyaltyAccountWithSignificantDiscountMembership.setLoyaltyAccountMembership(significantDiscountLoyaltyAccountMembership);

        double price = 1000;
        double expectedPriceForNoMembershipAccount = 1000;
        double expectedPriceForBasicLoyaltyAccount = 1000;
        double expectedPriceForDiscountLoyaltyAccount = 900;
        double expectedPriceForSignificantDiscountLoyaltyAccount = 500;

        Assertions.assertEquals(loyaltyAccountWithoutMembership.getDiscountedPrice(price), expectedPriceForNoMembershipAccount);
        Assertions.assertEquals(loyaltyAccountWithBasicMembership.getDiscountedPrice(price), expectedPriceForBasicLoyaltyAccount);
        Assertions.assertEquals(loyaltyAccountWithDiscountMembership.getDiscountedPrice(price), expectedPriceForDiscountLoyaltyAccount);
        Assertions.assertEquals(loyaltyAccountWithSignificantDiscountMembership.getDiscountedPrice(price), expectedPriceForSignificantDiscountLoyaltyAccount);

    }
}
