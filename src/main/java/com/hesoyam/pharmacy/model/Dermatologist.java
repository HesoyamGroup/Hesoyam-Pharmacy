/***********************************************************************
 * Module:  Dermatologist.java
 * Author:  WIN 10
 * Purpose: Defines the Class Dermatologist
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class Dermatologist extends Employee {
   private java.util.List<Pharmacy> pharmacy;
   
   
   /** @pdGenerated default getter */
   public java.util.List<Pharmacy> getPharmacy() {
      if (pharmacy == null)
         pharmacy = new java.util.ArrayList<Pharmacy>();
      return pharmacy;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPharmacy() {
      if (pharmacy == null)
         pharmacy = new java.util.ArrayList<Pharmacy>();
      return pharmacy.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPharmacy */
   public void setPharmacy(java.util.List<Pharmacy> newPharmacy) {
      removeAllPharmacy();
      for (java.util.Iterator iter = newPharmacy.iterator(); iter.hasNext();)
         addPharmacy((Pharmacy)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPharmacy */
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
   
   /** @pdGenerated default remove
     * @param oldPharmacy */
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
   
   /** @pdGenerated default removeAll */
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
   
   public CheckUp[] checkUp;

}