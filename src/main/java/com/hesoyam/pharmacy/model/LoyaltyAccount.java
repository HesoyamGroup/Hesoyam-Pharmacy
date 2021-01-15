/***********************************************************************
 * Module:  LoyaltyAccount.java
 * Author:  WIN 10
 * Purpose: Defines the Class LoyaltyAccount
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public class LoyaltyAccount {
   private int points;
   
   private Patient patient;
   
   private java.util.Collection<LoyaltyAccountMembership> loyaltyAccountMembership;

   public java.util.Collection<LoyaltyAccountMembership> getLoyaltyAccountMembership() {
      if (loyaltyAccountMembership == null)
         loyaltyAccountMembership = new java.util.HashSet<LoyaltyAccountMembership>();
      return loyaltyAccountMembership;
   }

   public java.util.Iterator getIteratorLoyaltyAccountMembership() {
      if (loyaltyAccountMembership == null)
         loyaltyAccountMembership = new java.util.HashSet<LoyaltyAccountMembership>();
      return loyaltyAccountMembership.iterator();
   }

   public void setLoyaltyAccountMembership(java.util.Collection<LoyaltyAccountMembership> newLoyaltyAccountMembership) {
      removeAllLoyaltyAccountMembership();
      for (java.util.Iterator iter = newLoyaltyAccountMembership.iterator(); iter.hasNext();)
         addLoyaltyAccountMembership((LoyaltyAccountMembership)iter.next());
   }

   public void addLoyaltyAccountMembership(LoyaltyAccountMembership newLoyaltyAccountMembership) {
      if (newLoyaltyAccountMembership == null)
         return;
      if (this.loyaltyAccountMembership == null)
         this.loyaltyAccountMembership = new java.util.HashSet<LoyaltyAccountMembership>();
      if (!this.loyaltyAccountMembership.contains(newLoyaltyAccountMembership))
         this.loyaltyAccountMembership.add(newLoyaltyAccountMembership);
   }

   public void removeLoyaltyAccountMembership(LoyaltyAccountMembership oldLoyaltyAccountMembership) {
      if (oldLoyaltyAccountMembership == null)
         return;
      if (this.loyaltyAccountMembership != null)
         if (this.loyaltyAccountMembership.contains(oldLoyaltyAccountMembership))
            this.loyaltyAccountMembership.remove(oldLoyaltyAccountMembership);
   }

   public void removeAllLoyaltyAccountMembership() {
      if (loyaltyAccountMembership != null)
         loyaltyAccountMembership.clear();
   }

}