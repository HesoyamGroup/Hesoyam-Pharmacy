/***********************************************************************
 * Module:  Inventory.java
 * Author:  WIN 10
 * Purpose: Defines the Class Inventory
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
public class Inventory {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
   @JoinColumn(name = "inventory_id", referencedColumnName = "id", nullable = false)
   private List<InventoryItem> inventoryItems;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="pharmacy_id", nullable = false)
   @JsonBackReference
   private Pharmacy pharmacy;

   public Inventory(){}

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }

   public List<InventoryItem> getInventoryItems() {
      if (inventoryItems == null)
         inventoryItems = new ArrayList<>();
      return inventoryItems;
   }

   public Iterator<InventoryItem> getIteratorInventoryItem() {
      if (inventoryItems == null)
         inventoryItems = new ArrayList<>();
      return inventoryItems.iterator();
   }

   public void setInventoryItems(List<InventoryItem> newInventoryItem) {
      removeAllInventoryItem();
      for (Iterator<InventoryItem> iter = newInventoryItem.iterator(); iter.hasNext();)
         addInventoryItem(iter.next());
   }

   public void addInventoryItem(InventoryItem newInventoryItem) {
      if (newInventoryItem == null)
         return;
      if (this.inventoryItems == null)
         this.inventoryItems = new ArrayList<>();
      if (!this.inventoryItems.contains(newInventoryItem))
         this.inventoryItems.add(newInventoryItem);
   }

   public void removeInventoryItem(InventoryItem oldInventoryItem) {
      if (oldInventoryItem == null)
         return;
      if (this.inventoryItems != null && this.inventoryItems.contains(oldInventoryItem))
            this.inventoryItems.remove(oldInventoryItem);
   }

   public void removeAllInventoryItem() {
      if (inventoryItems != null)
         inventoryItems.clear();
   }

}