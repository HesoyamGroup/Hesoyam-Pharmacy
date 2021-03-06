/***********************************************************************
 * Module:  Dermatologist.java
 * Author:  WIN 10
 * Purpose: Defines the Class Dermatologist
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.employee_management.model.Shift;
import com.hesoyam.pharmacy.employee_management.model.ShiftType;
import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Dermatologist extends Employee {

   @ManyToMany(mappedBy = "dermatologists")
   @JsonBackReference
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

   public boolean addPharmacy(Pharmacy newPharmacy) {
      if (newPharmacy == null)
         return false;
      if (this.pharmacies == null)
         this.pharmacies = new ArrayList<>();
      if (this.pharmacies.contains(newPharmacy)) {
         return false;
      } else{
         this.pharmacies.add(newPharmacy);
         newPharmacy.addDermatologist(this);
         return true;
      }
   }

   public boolean removePharmacy(Pharmacy oldPharmacy) {
      if (oldPharmacy == null)
         return false;
      if (this.pharmacies != null && this.pharmacies.contains(oldPharmacy)) {
         this.pharmacies.remove(oldPharmacy);
         oldPharmacy.removeDermatologist(this);
         return true;
      } else
         return false;
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

   public List<CheckUp> getCheckUps() {
      if (checkUps == null)
         checkUps = new ArrayList<>();
      return checkUps;
   }

   public Iterator<CheckUp> getIteratorCheckUps() {
      if (checkUps == null)
         checkUps = new ArrayList<>();
      return checkUps.iterator();
   }

   public void setCheckUps(List<CheckUp> newCheckUps) {
      removeAllCheckUps();
      for (CheckUp newCheckUp : newCheckUps) addCheckUps(newCheckUp);
   }

   public void addCheckUps(CheckUp newCheckUp) {
      if (newCheckUp == null)
         return;
      if (this.checkUps == null)
         this.checkUps = new ArrayList<>();
      if (!this.checkUps.contains(newCheckUp))
      {
         this.checkUps.add(newCheckUp);
         newCheckUp.setDermatologist(this);
      }
   }

   public void removeCheckUps(CheckUp oldCheckUp) {
      if (oldCheckUp == null)
         return;
      if (this.checkUps != null && this.checkUps.contains(oldCheckUp))
         {
            this.checkUps.remove(oldCheckUp);
            oldCheckUp.setDermatologist(null);
         }
   }

   public void removeAllCheckUps() {
      if (checkUps != null)
      {
         CheckUp oldCheckUp;
         for (Iterator<CheckUp> iter = getIteratorCheckUps(); iter.hasNext();)
         {
            oldCheckUp = iter.next();
            iter.remove();
            oldCheckUp.setDermatologist(null);
         }
      }
   }

   @Override
   public boolean isAdministratorMyBoss(User administrator) {
      return getPharmacies().stream().anyMatch(pharmacy -> pharmacy.getAdministrator().contains(administrator));
   }

   @Override
   public boolean isWorkingAt(Pharmacy pharmacy) {
      return getPharmacies().contains(pharmacy);
   }

   @Override
   protected boolean hasClearSchedule(DateTimeRange dateTimeRange) {
      return getCheckUps().stream().noneMatch(checkUp -> checkUp.isConflictingWith(dateTimeRange));
   }

   @Override
   public void addVacation(VacationRequest vacationRequest) {
      for (Pharmacy pharmacy: pharmacies) {
         Shift vacation = new Shift();
         vacation.setType(ShiftType.VACATION);
         vacation.setDateTimeRange(vacationRequest.getDateTimeRange());
         vacation.setPharmacy(pharmacy);
         addShift(vacation);
      }
   }

   @Override
   public boolean canRemovePharmacy(Pharmacy pharmacy) {
      return checkUps.stream().noneMatch(checkUp -> checkUp.isUpcoming() && checkUp.isTaken());
   }

   @Override
   public void clearAppointmentsForPharmacy(Pharmacy pharmacy) {
      List<CheckUp> checkUpsToRemove = getCheckUps().stream().filter(checkUp -> checkUp.getPharmacy().equals(pharmacy)).collect(Collectors.toList());
      getCheckUps().removeAll(checkUpsToRemove);
   }
}