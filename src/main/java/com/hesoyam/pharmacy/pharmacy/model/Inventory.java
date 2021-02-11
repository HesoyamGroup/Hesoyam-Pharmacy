/***********************************************************************
 * Module:  Inventory.java
 * Author:  WIN 10
 * Purpose: Defines the Class Inventory
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Inventory {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "inventory_id", referencedColumnName = "id", nullable = false)
   private List<InventoryItem> inventoryItems;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="pharmacy_id", nullable = false)
   @JsonBackReference
   private Pharmacy pharmacy;


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
      for (InventoryItem inventoryItem : newInventoryItem) addInventoryItem(inventoryItem);
   }

   public void addInventoryItem(InventoryItem newInventoryItem) {
      if (newInventoryItem == null)
         return;
      if (this.inventoryItems == null)
         this.inventoryItems = new ArrayList<>();
      this.inventoryItems.add(newInventoryItem);
   }

   public boolean removeInventoryItem(InventoryItem oldInventoryItem) {
      if (oldInventoryItem == null)
         return false;
      if(oldInventoryItem.canBeRemoved())
         return getInventoryItems().remove(oldInventoryItem);
      else
         return false;
   }

   public void removeAllInventoryItem() {
      if (inventoryItems != null)
         inventoryItems.clear();
   }

    public void placeOrder(Order newOrder) {
      for(OrderItem orderItem : newOrder.getOrderItems()){
         if(!containsMedicine(orderItem.getMedicine())){
            InventoryItem inventoryItem = new InventoryItem(orderItem.getMedicine());
            addInventoryItem(inventoryItem);
         }
      }
    }

   private boolean containsMedicine(Medicine medicine) {
      return getInventoryItems().stream().anyMatch(item -> item.containsMedicine(medicine));
   }

   public void placeOffer(Offer acceptingOffer) {
      this.placeOrder(acceptingOffer.getOrder());
      updateBalance(acceptingOffer.getOrder().getOrderItems());
   }

   private void updateBalance(List<OrderItem> orderItems) {
      for(OrderItem item : orderItems){
         getInventoryItems().stream().filter(i -> i.containsMedicine(item.getMedicine())).findFirst().ifPresent(inventoryItem -> inventoryItem.updateBalance(item));
      }
   }

    public boolean contains(InventoryItem inventoryItem) {
      return getInventoryItems().contains(inventoryItem);
    }
}