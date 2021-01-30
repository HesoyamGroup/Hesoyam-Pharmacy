/***********************************************************************
 * Module:  Pharmacist.java
 * Author:  WIN 10
 * Purpose: Defines the Class Pharmacist
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pharmacist extends Employee {

   @ManyToOne(fetch = FetchType.LAZY, optional = true)
   @JoinColumn(name="pharmacy_id", referencedColumnName = "id", nullable = true)
   @JsonBackReference
   private Pharmacy pharmacy;

   @OneToMany(fetch = FetchType.LAZY, mappedBy="pharmacist")
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

   @Override
   public boolean isAdministratorMyBoss(User administrator) {
      return getPharmacy().getAdministrator().contains(administrator);
   }
}