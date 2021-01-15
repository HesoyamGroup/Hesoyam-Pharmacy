/***********************************************************************
 * Module:  MedicineSpecification.java
 * Author:  Geri
 * Purpose: Defines the Class MedicineSpecification
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class MedicineSpecification {
   private java.util.List<Contraindication> contraindication;
   private java.util.List<CompositionItem> compositionItem;
   private Dosage dosage;
   private java.util.List<Medicine> replacemnetMedicines;
   private Medicine medicine;
   
   
   /** @pdGenerated default getter */
   public java.util.List<Contraindication> getContraindication() {
      if (contraindication == null)
         contraindication = new java.util.ArrayList<Contraindication>();
      return contraindication;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorContraindication() {
      if (contraindication == null)
         contraindication = new java.util.ArrayList<Contraindication>();
      return contraindication.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newContraindication */
   public void setContraindication(java.util.List<Contraindication> newContraindication) {
      removeAllContraindication();
      for (java.util.Iterator iter = newContraindication.iterator(); iter.hasNext();)
         addContraindication((Contraindication)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newContraindication */
   public void addContraindication(Contraindication newContraindication) {
      if (newContraindication == null)
         return;
      if (this.contraindication == null)
         this.contraindication = new java.util.ArrayList<Contraindication>();
      if (!this.contraindication.contains(newContraindication))
         this.contraindication.add(newContraindication);
   }
   
   /** @pdGenerated default remove
     * @param oldContraindication */
   public void removeContraindication(Contraindication oldContraindication) {
      if (oldContraindication == null)
         return;
      if (this.contraindication != null)
         if (this.contraindication.contains(oldContraindication))
            this.contraindication.remove(oldContraindication);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllContraindication() {
      if (contraindication != null)
         contraindication.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<CompositionItem> getCompositionItem() {
      if (compositionItem == null)
         compositionItem = new java.util.ArrayList<CompositionItem>();
      return compositionItem;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorCompositionItem() {
      if (compositionItem == null)
         compositionItem = new java.util.ArrayList<CompositionItem>();
      return compositionItem.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newCompositionItem */
   public void setCompositionItem(java.util.List<CompositionItem> newCompositionItem) {
      removeAllCompositionItem();
      for (java.util.Iterator iter = newCompositionItem.iterator(); iter.hasNext();)
         addCompositionItem((CompositionItem)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newCompositionItem */
   public void addCompositionItem(CompositionItem newCompositionItem) {
      if (newCompositionItem == null)
         return;
      if (this.compositionItem == null)
         this.compositionItem = new java.util.ArrayList<CompositionItem>();
      if (!this.compositionItem.contains(newCompositionItem))
         this.compositionItem.add(newCompositionItem);
   }
   
   /** @pdGenerated default remove
     * @param oldCompositionItem */
   public void removeCompositionItem(CompositionItem oldCompositionItem) {
      if (oldCompositionItem == null)
         return;
      if (this.compositionItem != null)
         if (this.compositionItem.contains(oldCompositionItem))
            this.compositionItem.remove(oldCompositionItem);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllCompositionItem() {
      if (compositionItem != null)
         compositionItem.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<Medicine> getReplacemnetMedicines() {
      if (replacemnetMedicines == null)
         replacemnetMedicines = new java.util.ArrayList<Medicine>();
      return replacemnetMedicines;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorReplacemnetMedicines() {
      if (replacemnetMedicines == null)
         replacemnetMedicines = new java.util.ArrayList<Medicine>();
      return replacemnetMedicines.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newReplacemnetMedicines */
   public void setReplacemnetMedicines(java.util.List<Medicine> newReplacemnetMedicines) {
      removeAllReplacemnetMedicines();
      for (java.util.Iterator iter = newReplacemnetMedicines.iterator(); iter.hasNext();)
         addReplacemnetMedicines((Medicine)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMedicine */
   public void addReplacemnetMedicines(Medicine newMedicine) {
      if (newMedicine == null)
         return;
      if (this.replacemnetMedicines == null)
         this.replacemnetMedicines = new java.util.ArrayList<Medicine>();
      if (!this.replacemnetMedicines.contains(newMedicine))
         this.replacemnetMedicines.add(newMedicine);
   }
   
   /** @pdGenerated default remove
     * @param oldMedicine */
   public void removeReplacemnetMedicines(Medicine oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.replacemnetMedicines != null)
         if (this.replacemnetMedicines.contains(oldMedicine))
            this.replacemnetMedicines.remove(oldMedicine);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllReplacemnetMedicines() {
      if (replacemnetMedicines != null)
         replacemnetMedicines.clear();
   }

}