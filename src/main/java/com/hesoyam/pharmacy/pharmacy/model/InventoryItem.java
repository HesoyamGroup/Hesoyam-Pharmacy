/***********************************************************************
 * Module:  InvetoryItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class InvetoryItem
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "inventory_item_id", referencedColumnName = "id", nullable = false)
   private List<InventoryItemPrice> prices;

   public InventoryItem(){}

   public InventoryItem(Medicine medicine, int available, int reserved){
      this.medicine = medicine;
      this.available = available;
      this.reserved = reserved;
   }

   public InventoryItem(Medicine medicine){
      this.medicine = medicine;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof InventoryItem)) return false;
      InventoryItem that = (InventoryItem) o;
      return getId().equals(that.getId());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId());
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

   public double calculateTodayPriceForQuantity(int quantity){
      return quantity * getTodayPrice();
   }
   public double getTodayPrice(){
      for(InventoryItemPrice inventoryItemPrice : prices){
         DateTimeRange dateTimeRange = inventoryItemPrice.getValidThrough();
         if(dateTimeRange.includes(LocalDateTime.now())){
            return inventoryItemPrice.getPrice();
         }
      }
      return 0;
   }


   public boolean addPrices(InventoryItemPrice newInventoryItemPrice) {
      if (newInventoryItemPrice == null)
         return false;
      if (this.prices == null)
         this.prices = new ArrayList<>();

      if (!this.prices.contains(newInventoryItemPrice) && !datesAreConflicting(newInventoryItemPrice)) {
         this.prices.add(newInventoryItemPrice);
         return true;
      }
      return false;
   }

   public boolean updatePrices(InventoryItemPrice updatedItemPrice){
      if(updatedItemPrice == null)
         return false;
      if(this.prices == null)
         this.prices = new ArrayList<>();
      if(!this.prices.contains(updatedItemPrice))
         return false;
      if(!datesAreConflictingForUpdate(updatedItemPrice)){
         this.prices.set(this.prices.indexOf(updatedItemPrice), updatedItemPrice);
         return true;
      }
      return false;
   }

   public void removePrices(InventoryItemPrice oldInventoryItemPrice) {
      if (oldInventoryItemPrice == null)
         return;
      if (this.prices != null && this.prices.contains(oldInventoryItemPrice)) {
         this.prices.remove(oldInventoryItemPrice);
      }
   }

   public void removeAllPrices() {
      if (prices != null) {
         for (Iterator<InventoryItemPrice> iter = getIteratorPrices(); iter.hasNext();) {
            iter.remove();
         }
      }
   }

   private boolean datesAreConflicting(InventoryItemPrice newItemPrice){
      return this.getPrices().stream().anyMatch(itemPrice -> itemPrice.isConflictingWith(newItemPrice));
   }

   private boolean datesAreConflictingForUpdate(InventoryItemPrice updatedItemPrice){
      return this.getPrices().stream().anyMatch(itemPrice -> !itemPrice.equals(updatedItemPrice) && itemPrice.isConflictingWith(updatedItemPrice));
   }

   public boolean containsMedicine(Medicine medicine) {
      return getMedicine().equals(medicine);
   }

    public void updateBalance(OrderItem item) {
      this.available += item.getQuantity();
   }

   public boolean canBeRemoved() {
      return this.reserved == 0;
   }
}