/***********************************************************************
 * Module:  Administrator.java
 * Author:  WIN 10
 * Purpose: Defines the Class Administrator
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Administrator extends User {

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name="pharmacy_id", nullable=false)
   @JsonBackReference
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

   @Override
   public boolean isEnabled() {
      return true;
   }

}