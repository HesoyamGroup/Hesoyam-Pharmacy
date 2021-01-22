/***********************************************************************
 * Module:  Dermatologist.java
 * Author:  WIN 10
 * Purpose: Defines the Class Dermatologist
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Dermatologist extends Employee {

   @ManyToMany(mappedBy = "dermatologists")
   private List<Pharmacy> pharmacies;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "dermatologist")
   private List<CheckUp> checkUps;

   public List<Pharmacy> getPharmacies() {
      if (pharmacies == null)
         pharmacies = new ArrayList<>();
      return pharmacies;
   }

   public Iterator<Pharmacy> getIteratorPharmacy() {
      if (pharmacies == null)
         pharmacies = new ArrayList<>();
      return pharmacies.iterator();
   }

   public void setPharmacies(List<Pharmacy> newPharmacy) {
      removeAllPharmacy();
      for (Iterator<Pharmacy> iter = newPharmacy.iterator(); iter.hasNext();)
         addPharmacy(iter.next());
   }

   public void addPharmacy(Pharmacy newPharmacy) {
      if (newPharmacy == null)
         return;
      if (this.pharmacies == null)
         this.pharmacies = new ArrayList<>();
      if (!this.pharmacies.contains(newPharmacy))
      {
         this.pharmacies.add(newPharmacy);
         newPharmacy.addDermatologist(this);      
      }
   }

   public void removePharmacy(Pharmacy oldPharmacy) {
      if (oldPharmacy == null)
         return;
      if (this.pharmacies != null && this.pharmacies.contains(oldPharmacy)) {
         this.pharmacies.remove(oldPharmacy);
         oldPharmacy.removeDermatologist(this);
      }
   }

   public void removeAllPharmacy() {
      if (pharmacies != null)
      {
         Pharmacy oldPharmacy;
         for (Iterator<Pharmacy> iter = getIteratorPharmacy(); iter.hasNext();)
         {
            oldPharmacy = iter.next();
            iter.remove();
            oldPharmacy.removeDermatologist(this);
         }
      }
   }
}