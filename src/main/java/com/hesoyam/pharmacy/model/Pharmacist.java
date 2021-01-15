/***********************************************************************
 * Module:  Pharmacist.java
 * Author:  WIN 10
 * Purpose: Defines the Class Pharmacist
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class Pharmacist extends Employee {
   private Pharmacy pharmacy;
   
   
   /** @pdGenerated default parent getter */
   public Pharmacy getPharmacy() {
      return pharmacy;
   }
   
   /** @pdGenerated default parent setter
     * @param newPharmacy */
   public void setPharmacy(Pharmacy newPharmacy) {
      if (this.pharmacy == null || !this.pharmacy.equals(newPharmacy))
      {
         if (this.pharmacy != null)
         {
            Pharmacy oldPharmacy = this.pharmacy;
            this.pharmacy = null;
            oldPharmacy.removePharmacist(this);
         }
         if (newPharmacy != null)
         {
            this.pharmacy = newPharmacy;
            this.pharmacy.addPharmacist(this);
         }
      }
   }
   
   public Counseling[] counseling;

}