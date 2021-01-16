/***********************************************************************
 * Module:  Pharmacist.java
 * Author:  WIN 10
 * Purpose: Defines the Class Pharmacist
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import java.util.List;

public class Pharmacist extends Employee {
   private Pharmacy pharmacy;
   private List<Counseling> counselings;

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

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
   


}