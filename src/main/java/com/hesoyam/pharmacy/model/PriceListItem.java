/***********************************************************************
 * Module:  PriceListItem.java
 * Author:  WIN 10
 * Purpose: Defines the Class PriceListItem
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

public class PriceListItem {
   public java.util.Collection<ItemPrice> itemPrice;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<ItemPrice> getItemPrice() {
      if (itemPrice == null)
         itemPrice = new java.util.HashSet<ItemPrice>();
      return itemPrice;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorItemPrice() {
      if (itemPrice == null)
         itemPrice = new java.util.HashSet<ItemPrice>();
      return itemPrice.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newItemPrice */
   public void setItemPrice(java.util.Collection<ItemPrice> newItemPrice) {
      removeAllItemPrice();
      for (java.util.Iterator iter = newItemPrice.iterator(); iter.hasNext();)
         addItemPrice((ItemPrice)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newItemPrice */
   public void addItemPrice(ItemPrice newItemPrice) {
      if (newItemPrice == null)
         return;
      if (this.itemPrice == null)
         this.itemPrice = new java.util.HashSet<ItemPrice>();
      if (!this.itemPrice.contains(newItemPrice))
         this.itemPrice.add(newItemPrice);
   }
   
   /** @pdGenerated default remove
     * @param oldItemPrice */
   public void removeItemPrice(ItemPrice oldItemPrice) {
      if (oldItemPrice == null)
         return;
      if (this.itemPrice != null)
         if (this.itemPrice.contains(oldItemPrice))
            this.itemPrice.remove(oldItemPrice);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllItemPrice() {
      if (itemPrice != null)
         itemPrice.clear();
   }

}