/***********************************************************************
 * Module:  Patient.java
 * Author:  WIN 10
 * Purpose: Defines the Class Patient
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class Patient extends User {
   private int penaltyPoints;
   
   private java.util.List<Medicine> allergies;
   private LoyaltyAccount loyaltyAccount;
   
   
   /** @pdGenerated default getter */
   public java.util.List<Medicine> getAllergies() {
      if (allergies == null)
         allergies = new java.util.ArrayList<Medicine>();
      return allergies;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAllergies() {
      if (allergies == null)
         allergies = new java.util.ArrayList<Medicine>();
      return allergies.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAllergies */
   public void setAllergies(java.util.List<Medicine> newAllergies) {
      removeAllAllergies();
      for (java.util.Iterator iter = newAllergies.iterator(); iter.hasNext();)
         addAllergies((Medicine)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMedicine */
   public void addAllergies(Medicine newMedicine) {
      if (newMedicine == null)
         return;
      if (this.allergies == null)
         this.allergies = new java.util.ArrayList<Medicine>();
      if (!this.allergies.contains(newMedicine))
         this.allergies.add(newMedicine);
   }
   
   /** @pdGenerated default remove
     * @param oldMedicine */
   public void removeAllergies(Medicine oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.allergies != null)
         if (this.allergies.contains(oldMedicine))
            this.allergies.remove(oldMedicine);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAllergies() {
      if (allergies != null)
         allergies.clear();
   }

}