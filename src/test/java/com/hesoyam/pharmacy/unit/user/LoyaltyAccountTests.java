package com.hesoyam.pharmacy.unit.user;

import com.hesoyam.pharmacy.user.model.LoyaltyAccount;
import com.hesoyam.pharmacy.user.model.LoyaltyAccountMembership;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class LoyaltyAccountTests {

    @Test
    public void calculateLoyaltyAccountDiscountTest(){
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

        Assert.assertTrue(loyaltyAccountWithoutMembership.getDiscountedPrice(price) == expectedPriceForNoMembershipAccount);
        Assert.assertTrue(loyaltyAccountWithBasicMembership.getDiscountedPrice(price) == expectedPriceForBasicLoyaltyAccount);
        Assert.assertTrue(loyaltyAccountWithDiscountMembership.getDiscountedPrice(price) == expectedPriceForDiscountLoyaltyAccount);
        Assert.assertTrue(loyaltyAccountWithSignificantDiscountMembership.getDiscountedPrice(price) == expectedPriceForSignificantDiscountLoyaltyAccount);

    }
}
