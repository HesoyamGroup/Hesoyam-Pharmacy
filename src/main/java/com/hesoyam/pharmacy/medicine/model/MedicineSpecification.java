/***********************************************************************
 * Module:  MedicineSpecification.java
 * Author:  Geri
 * Purpose: Defines the Class MedicineSpecification
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
public class MedicineSpecification {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "medicine_contraindication",
           joinColumns = @JoinColumn(name="medicine_specification_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "contraindication_id", referencedColumnName = "id"))
   private List<Contraindication> contraindications;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "medicine_composition",
           joinColumns = @JoinColumn(name = "medicine_specification_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "composition_id", referencedColumnName = "id"))
   private List<CompositionItem> compositionItems;

   @Embedded
   private Dosage dosage;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "replacement_medicine",
           joinColumns = @JoinColumn(name = "medicine_specification_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "replacement_medicine_id", referencedColumnName = "id"))
   private List<Medicine> replacementMedicines;

   @OneToOne(fetch = FetchType.EAGER, mappedBy = "medicineSpecification")
   private Medicine medicine;

   public List<Contraindication> getContraindications() {
      if (contraindications == null)
         contraindications = new ArrayList<Contraindication>();
      return contraindications;
   }

   public Iterator getIteratorContraindication() {
      if (contraindications == null)
         contraindications = new ArrayList<Contraindication>();
      return contraindications.iterator();
   }

   public void setContraindications(List<Contraindication> newContraindication) {
      removeAllContraindication();
      for (Iterator iter = newContraindication.iterator(); iter.hasNext();)
         addContraindication((Contraindication)iter.next());
   }

   public void addContraindication(Contraindication newContraindication) {
      if (newContraindication == null)
         return;
      if (this.contraindications == null)
         this.contraindications = new ArrayList<Contraindication>();
      if (!this.contraindications.contains(newContraindication))
         this.contraindications.add(newContraindication);
   }

   public void removeContraindication(Contraindication oldContraindication) {
      if (oldContraindication == null)
         return;
      if (this.contraindications != null)
         if (this.contraindications.contains(oldContraindication))
            this.contraindications.remove(oldContraindication);
   }

   public void removeAllContraindication() {
      if (contraindications != null)
         contraindications.clear();
   }

   public List<CompositionItem> getCompositionItems() {
      if (compositionItems == null)
         compositionItems = new ArrayList<CompositionItem>();
      return compositionItems;
   }

   public Iterator getIteratorCompositionItem() {
      if (compositionItems == null)
         compositionItems = new ArrayList<CompositionItem>();
      return compositionItems.iterator();
   }

   public void setCompositionItems(List<CompositionItem> newCompositionItem) {
      removeAllCompositionItem();
      for (Iterator iter = newCompositionItem.iterator(); iter.hasNext();)
         addCompositionItem((CompositionItem)iter.next());
   }

   public void addCompositionItem(CompositionItem newCompositionItem) {
      if (newCompositionItem == null)
         return;
      if (this.compositionItems == null)
         this.compositionItems = new ArrayList<CompositionItem>();
      if (!this.compositionItems.contains(newCompositionItem))
         this.compositionItems.add(newCompositionItem);
   }

   public void removeCompositionItem(CompositionItem oldCompositionItem) {
      if (oldCompositionItem == null)
         return;
      if (this.compositionItems != null)
         if (this.compositionItems.contains(oldCompositionItem))
            this.compositionItems.remove(oldCompositionItem);
   }

   public void removeAllCompositionItem() {
      if (compositionItems != null)
         compositionItems.clear();
   }

   public List<Medicine> getReplacementMedicines() {
      if (replacementMedicines == null)
         replacementMedicines = new ArrayList<Medicine>();
      return replacementMedicines;
   }

   public Iterator getIteratorReplacementMedicines() {
      if (replacementMedicines == null)
         replacementMedicines = new ArrayList<Medicine>();
      return replacementMedicines.iterator();
   }

   public void setReplacementMedicines(List<Medicine> newReplacementMedicines) {
      removeAllReplacementMedicines();
      for (Iterator iter = newReplacementMedicines.iterator(); iter.hasNext();)
         addReplacementMedicines((Medicine)iter.next());
   }

   public void addReplacementMedicines(Medicine newMedicine) {
      if (newMedicine == null)
         return;
      if (this.replacementMedicines == null)
         this.replacementMedicines = new ArrayList<Medicine>();
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