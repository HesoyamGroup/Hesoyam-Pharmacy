/***********************************************************************
 * Module:  InvetoryItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class InvetoryItem
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.*;

@Entity
public class InventoryItem {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   @Min(0)
   private int available;
   @Column
   @Min(0)
   private int reserved;

   @ManyToOne(fetch=FetchType.EAGER, optional=false)
   @JoinColumn(name="medicine_id")
   private Medicine medicine;

   @OneToMany(mappedBy = "inventoryItem", cascade = CascadeType.ALL)
   private List<InventoryItemPrice> prices;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public int getAvailable() {
      return available;
   }

   public void setAvailable(int available) {
      this.available = available;
   }

   public int getReserved() {
      return reserved;
   }

   public void setReserved(int reserved) {
      this.reserved = reserved;
   }

   public Medicine getMedicine() {
      return medicine;
   }

   public void setMedicine(Medicine medicine) {
      this.medicine = medicine;
   }

   public void update(InventoryItem inventoryItem){
      this.id = inventoryItem.getId();
      this.available = inventoryItem.getAvailable();
      this.reserved = inventoryItem.getReserved();
      this.medicine = inventoryItem.getMedicine();
      this.prices = inventoryItem.getPrices();
   }

   public List<InventoryItemPrice> getPrices() {
      if (prices == null)
         prices = new ArrayList<>();
      return prices;
   }

   public Iterator<InventoryItemPrice> getIteratorPrices() {
      if (prices == null)
         prices = new ArrayList<>();
      return prices.iterator();
   }

   public void setPrices(List<InventoryItemPrice> newPrices) {
      removeAllPrices();
      for (Iterator<InventoryItemPrice> iter = newPrices.iterator(); iter.hasNext();)
         addPrices(iter.next());
   }

   public void addPrices(InventoryItemPrice newInventoryItemPrice) {
      if (newInventoryItemPrice == null)
         return;
      if (this.prices == null)
         this.prices = new ArrayList<>();
      if (!this.prices.contains(newInventoryItemPrice)) {
         this.prices.add(newInventoryItemPrice);
         newInventoryItemPrice.setInventoryItem(this);
      }
   }

   public void removePrices(InventoryItemPrice oldInventoryItemPrice) {
      if (oldInventoryItemPrice == null)
         return;
      if (this.prices != null && this.prices.contains(oldInventoryItemPrice)) {
         this.prices.remove(oldInventoryItemPrice);
         oldInventoryItemPrice.setInventoryItem((InventoryItem)null);
      }
   }

   public void removeAllPrices() {
      if (prices != null) {
         InventoryItemPrice oldInventoryItemPrice;
         for (Iterator<InventoryItemPrice> iter = getIteratorPrices(); iter.hasNext();) {
            oldInventoryItemPrice = iter.next();
            iter.remove();
            oldInventoryItemPrice.setInventoryItem((InventoryItem)null);
         }
      }
   }

}