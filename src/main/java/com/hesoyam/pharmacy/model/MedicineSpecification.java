/***********************************************************************
 * Module:  MedicineSpecification.java
 * Author:  Geri
 * Purpose: Defines the Class MedicineSpecification
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public class MedicineSpecification {
   private java.util.List<Contraindication> contraindication;
   private java.util.List<CompositionItem> compositionItem;
   private Dosage dosage;
   private java.util.List<Medicine> replacementMedicines;
   private Medicine medicine;

   public java.util.List<Contraindication> getContraindication() {
      if (contraindication == null)
         contraindication = new java.util.ArrayList<Contraindication>();
      return contraindication;
   }

   public java.util.Iterator getIteratorContraindication() {
      if (contraindication == null)
         contraindication = new java.util.ArrayList<Contraindication>();
      return contraindication.iterator();
   }

   public void setContraindication(java.util.List<Contraindication> newContraindication) {
      removeAllContraindication();
      for (java.util.Iterator iter = newContraindication.iterator(); iter.hasNext();)
         addContraindication((Contraindication)iter.next());
   }

   public void addContraindication(Contraindication newContraindication) {
      if (newContraindication == null)
         return;
      if (this.contraindication == null)
         this.contraindication = new java.util.ArrayList<Contraindication>();
      if (!this.contraindication.contains(newContraindication))
         this.contraindication.add(newContraindication);
   }

   public void removeContraindication(Contraindication oldContraindication) {
      if (oldContraindication == null)
         return;
      if (this.contraindication != null)
         if (this.contraindication.contains(oldContraindication))
            this.contraindication.remove(oldContraindication);
   }

   public void removeAllContraindication() {
      if (contraindication != null)
         contraindication.clear();
   }

   public java.util.List<CompositionItem> getCompositionItem() {
      if (compositionItem == null)
         compositionItem = new java.util.ArrayList<CompositionItem>();
      return compositionItem;
   }

   public java.util.Iterator getIteratorCompositionItem() {
      if (compositionItem == null)
         compositionItem = new java.util.ArrayList<CompositionItem>();
      return compositionItem.iterator();
   }

   public void setCompositionItem(java.util.List<CompositionItem> newCompositionItem) {
      removeAllCompositionItem();
      for (java.util.Iterator iter = newCompositionItem.iterator(); iter.hasNext();)
         addCompositionItem((CompositionItem)iter.next());
   }

   public void addCompositionItem(CompositionItem newCompositionItem) {
      if (newCompositionItem == null)
         return;
      if (this.compositionItem == null)
         this.compositionItem = new java.util.ArrayList<CompositionItem>();
      if (!this.compositionItem.contains(newCompositionItem))
         this.compositionItem.add(newCompositionItem);
   }

   public void removeCompositionItem(CompositionItem oldCompositionItem) {
      if (oldCompositionItem == null)
         return;
      if (this.compositionItem != null)
         if (this.compositionItem.contains(oldCompositionItem))
            this.compositionItem.remove(oldCompositionItem);
   }

   public void removeAllCompositionItem() {
      if (compositionItem != null)
         compositionItem.clear();
   }

   public java.util.List<Medicine> getReplacementMedicines() {
      if (replacementMedicines == null)
         replacementMedicines = new java.util.ArrayList<Medicine>();
      return replacementMedicines;
   }

   public java.util.Iterator getIteratorReplacementMedicines() {
      if (replacementMedicines == null)
         replacementMedicines = new java.util.ArrayList<Medicine>();
      return replacementMedicines.iterator();
   }

   public void setReplacementMedicines(java.util.List<Medicine> newReplacementMedicines) {
      removeAllReplacementMedicines();
      for (java.util.Iterator iter = newReplacementMedicines.iterator(); iter.hasNext();)
         addReplacementMedicines((Medicine)iter.next());
   }

   public void addReplacementMedicines(Medicine newMedicine) {
      if (newMedicine == null)
         return;
      if (this.replacementMedicines == null)
         this.replacementMedicines = new java.util.ArrayList<Medicine>();
      if (!this.replacementMedicines.contains(newMedicine))
         this.replacementMedicines.add(newMedicine);
   }

   public void removeReplacementMedicines(Medicine oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.replacementMedicines != null)
         if (this.replacementMedicines.contains(oldMedicine))
            this.replacementMedicines.remove(oldMedicine);
   }

   public void removeAllReplacementMedicines() {
      if (replacementMedicines != null)
         replacementMedicines.clear();
   }

}