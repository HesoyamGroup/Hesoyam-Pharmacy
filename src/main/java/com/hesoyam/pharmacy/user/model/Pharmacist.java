/***********************************************************************
 * Module:  Pharmacist.java
 * Author:  WIN 10
 * Purpose: Defines the Class Pharmacist
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
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

   public List<Counseling> getCounselings() {
      if (counselings == null)
         counselings = new ArrayList<>();
      return counselings;
   }

   public Iterator<Counseling> getIteratorCounselings() {
      if (counselings == null)
         counselings = new ArrayList<>();
      return counselings.iterator();
   }

   public void setCounselings(List<Counseling> newCounselings) {
      removeAllCounselings();
      for (Counseling newCounseling : newCounselings) addCounselings(newCounseling);
   }

   public void addCounselings(Counseling newCounseling) {
      if (newCounseling == null)
         return;
      if (this.counselings == null)
         this.counselings = new ArrayList<>();
      if (!this.counselings.contains(newCounseling))
      {
         this.counselings.add(newCounseling);
         newCounseling.setPharmacist(this);
      }
   }

   public void removeCounselings(Counseling oldCounseling) {
      if (oldCounseling == null)
         return;
      if (this.counselings != null && this.counselings.contains(oldCounseling)) {
            this.counselings.remove(oldCounseling);
            oldCounseling.setPharmacist(null);
      }
   }

   public void removeAllCounselings() {
      if (counselings != null)
      {
         Counseling oldCounseling;
         for (Iterator<Counseling> iter = getIteratorCounselings(); iter.hasNext();)
         {
            oldCounseling = iter.next();
            iter.remove();
            oldCounseling.setPharmacist(null);
         }
      }
   }

   @Override
   public boolean isAdministratorMyBoss(User administrator) {
      return getPharmacy().getAdministrator().contains(administrator);
   }

   @Override
   public boolean hasClearSchedule(DateTimeRange dateTimeRange) {
      return getCounselings().stream().noneMatch(counseling -> counseling.isConflictingWith(dateTimeRange));
   }
}