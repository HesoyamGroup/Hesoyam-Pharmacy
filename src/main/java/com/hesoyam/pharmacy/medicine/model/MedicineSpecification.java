/***********************************************************************
 * Module:  MedicineSpecification.java
 * Author:  Geri
 * Purpose: Defines the Class MedicineSpecification
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

   @Valid
   @Embedded
   @NotNull(message = "Dosage must be specified.")
   private Dosage dosage;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "replacement_medicine",
           joinColumns = @JoinColumn(name = "medicine_specification_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "replacement_medicine_id", referencedColumnName = "id"))
   private List<Medicine> replacementMedicines;


   public List<Contraindication> getContraindications() {
      if (contraindications == null)
         contraindications = new ArrayList<>();
      return contraindications;
   }

   public Iterator<Contraindication> getIteratorContraindication() {
      if (contraindications == null)
         contraindications = new ArrayList<>();
      return contraindications.iterator();
   }

   public void setContraindications(List<Contraindication> newContraindication) {
      removeAllContraindication();
      for (Iterator<Contraindication> iter = newContraindication.iterator(); iter.hasNext();)
         addContraindication(iter.next());
   }

   public void addContraindication(Contraindication newContraindication) {
      if (newContraindication == null)
         return;
      if (this.contraindications == null)
         this.contraindications = new ArrayList<>();
      if (!this.contraindications.contains(newContraindication))
         this.contraindications.add(newContraindication);
   }

   public void removeContraindication(Contraindication oldContraindication) {
      if (oldContraindication == null)
         return;
      if (this.contraindications != null && this.contraindications.contains(oldContraindication))
            this.contraindications.remove(oldContraindication);
   }

   public void removeAllContraindication() {
      if (contraindications != null)
         contraindications.clear();
   }

   public List<CompositionItem> getCompositionItems() {
      if (compositionItems == null)
         compositionItems = new ArrayList<>();
      return compositionItems;
   }

   public Iterator<CompositionItem> getIteratorCompositionItem() {
      if (compositionItems == null)
         compositionItems = new ArrayList<>();
      return compositionItems.iterator();
   }

   public void setCompositionItems(List<CompositionItem> newCompositionItem) {
      removeAllCompositionItem();
      for (Iterator<CompositionItem> iter = newCompositionItem.iterator(); iter.hasNext();)
         addCompositionItem(iter.next());
   }

   public void addCompositionItem(CompositionItem newCompositionItem) {
      if (newCompositionItem == null)
         return;
      if (this.compositionItems == null)
         this.compositionItems = new ArrayList<>();
      if (!this.compositionItems.contains(newCompositionItem))
         this.compositionItems.add(newCompositionItem);
   }

   public void removeCompositionItem(CompositionItem oldCompositionItem) {
      if (oldCompositionItem == null)
         return;
      if (this.compositionItems != null && this.compositionItems.contains(oldCompositionItem))
            this.compositionItems.remove(oldCompositionItem);
   }

   public void removeAllCompositionItem() {
      if (compositionItems != null)
         compositionItems.clear();
   }

   public List<Medicine> getReplacementMedicines() {
      if (replacementMedicines == null)
         replacementMedicines = new ArrayList<>();
      return replacementMedicines;
   }

   public Iterator<Medicine> getIteratorReplacementMedicines() {
      if (replacementMedicines == null)
         replacementMedicines = new ArrayList<>();
      return replacementMedicines.iterator();
   }

   public void setReplacementMedicines(List<Medicine> newReplacementMedicines) {
      removeAllReplacementMedicines();
      for (Iterator<Medicine> iter = newReplacementMedicines.iterator(); iter.hasNext();)
         addReplacementMedicines(iter.next());
   }

   public void addReplacementMedicines(Medicine newMedicine) {
      if (newMedicine == null)
         return;
      if (this.replacementMedicines == null)
         this.replacementMedicines = new ArrayList<>();
      if (!this.replacementMedicines.contains(newMedicine))
         this.replacementMedicines.add(newMedicine);
   }

   public void removeReplacementMedicines(Medicine oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.replacementMedicines != null && this.replacementMedicines.contains(oldMedicine))
            this.replacementMedicines.remove(oldMedicine);
   }

   public void removeAllReplacementMedicines() {
      if (replacementMedicines != null)
         replacementMedicines.clear();
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Dosage getDosage() {
      return dosage;
   }

   public void setDosage(Dosage dosage) {
      this.dosage = dosage;
   }
}