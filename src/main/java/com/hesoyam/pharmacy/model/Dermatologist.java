/***********************************************************************
 * Module:  Dermatologist.java
 * Author:  WIN 10
 * Purpose: Defines the Class Dermatologist
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public class Dermatologist extends Employee {
   private java.util.List<Pharmacy> pharmacy;
   private CheckUp[] checkUp;

   public java.util.List<Pharmacy> getPharmacy() {
      if (pharmacy == null)
         pharmacy = new java.util.ArrayList<Pharmacy>();
      return pharmacy;
   }

   public java.util.Iterator getIteratorPharmacy() {
      if (pharmacy == null)
         pharmacy = new java.util.ArrayList<Pharmacy>();
      return pharmacy.iterator();
   }

   public void setPharmacy(java.util.List<Pharmacy> newPharmacy) {
      removeAllPharmacy();
      for (java.util.Iterator iter = newPharmacy.iterator(); iter.hasNext();)
         addPharmacy((Pharmacy)iter.next());
   }

   public void addPharmacy(Pharmacy newPharmacy) {
      if (newPharmacy == null)
         return;
      if (this.pharmacy == null)
         this.pharmacy = new java.util.ArrayList<Pharmacy>();
      if (!this.pharmacy.contains(newPharmacy))
      {
         this.pharmacy.add(newPharmacy);
         newPharmacy.addDermatologist(this);      
      }
   }

   public void removePharmacy(Pharmacy oldPharmacy) {
      if (oldPharmacy == null)
         return;
      if (this.pharmacy != null)
         if (this.pharmacy.contains(oldPharmacy))
         {
            this.pharmacy.remove(oldPharmacy);
            oldPharmacy.removeDermatologist(this);
         }
   }

   public void removeAllPharmacy() {
      if (pharmacy != null)
      {
         Pharmacy oldPharmacy;
         for (java.util.Iterator iter = getIteratorPharmacy(); iter.hasNext();)
         {
            oldPharmacy = (Pharmacy)iter.next();
            iter.remove();
            oldPharmacy.removeDermatologist(this);
         }
      }
   }
}