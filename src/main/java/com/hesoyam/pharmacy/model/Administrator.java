/***********************************************************************
 * Module:  Administrator.java
 * Author:  WIN 10
 * Purpose: Defines the Class Administrator
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public class Administrator extends User {
   private Pharmacy pharmacy;

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
            oldPharmacy.removeAdministrator(this);
         }
         if (newPharmacy != null)
         {
            this.pharmacy = newPharmacy;
            this.pharmacy.addAdministrator(this);
         }
      }
   }

}