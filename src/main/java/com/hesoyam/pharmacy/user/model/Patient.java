/***********************************************************************
 * Module:  Patient.java
 * Author:  WIN 10
 * Purpose: Defines the Class Patient
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Patient extends User {

   @Column
   @Min(0)
   private int penaltyPoints;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name="patient_allergies", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name="medicine_id", referencedColumnName="id"))
   private List<Medicine> allergies;

   @OneToOne(optional = true, fetch = FetchType.LAZY, mappedBy = "patient")
   private LoyaltyAccount loyaltyAccount;

   public Patient(){}


   public List<Medicine> getAllergies() {
      if (allergies == null)
         allergies = new ArrayList<>();
      return allergies;
   }

   public Iterator<Medicine> getIteratorAllergies() {
      if (allergies == null)
         allergies = new ArrayList<>();
      return allergies.iterator();
   }

   public void setAllergies(List<Medicine> newAllergies) {
      removeAllAllergies();
      for (Iterator<Medicine> iter = newAllergies.iterator(); iter.hasNext();)
         addAllergies(iter.next());
   }

   public void addAllergies(Medicine newMedicine) {
      if (newMedicine == null)
         return;
      if (this.allergies == null)
         this.allergies = new ArrayList<>();
      if (!this.allergies.contains(newMedicine))
         this.allergies.add(newMedicine);
   }

   public void removeAllergies(Medicine oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.allergies != null && this.allergies.contains(oldMedicine))
            this.allergies.remove(oldMedicine);
   }

   public void removeAllAllergies() {
      if (allergies != null)
         allergies.clear();
   }





}