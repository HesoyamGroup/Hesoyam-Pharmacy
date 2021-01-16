/***********************************************************************
 * Module:  Patient.java
 * Author:  WIN 10
 * Purpose: Defines the Class Patient
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Patient extends User {
   private int penaltyPoints;
   private List<Medicine> allergies;
   private LoyaltyAccount loyaltyAccount;

   public List<Medicine> getAllergies() {
      if (allergies == null)
         allergies = new ArrayList<Medicine>();
      return allergies;
   }

   public Iterator getIteratorAllergies() {
      if (allergies == null)
         allergies = new ArrayList<Medicine>();
      return allergies.iterator();
   }

   public void setAllergies(List<Medicine> newAllergies) {
      removeAllAllergies();
      for (Iterator iter = newAllergies.iterator(); iter.hasNext();)
         addAllergies((Medicine)iter.next());
   }

   public void addAllergies(Medicine newMedicine) {
      if (newMedicine == null)
         return;
      if (this.allergies == null)
         this.allergies = new ArrayList<Medicine>();
      if (!this.allergies.contains(newMedicine))
         this.allergies.add(newMedicine);
   }

   public void removeAllergies(Medicine oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.allergies != null)
         if (this.allergies.contains(oldMedicine))
            this.allergies.remove(oldMedicine);
   }

   public void removeAllAllergies() {
      if (allergies != null)
         allergies.clear();
   }

}