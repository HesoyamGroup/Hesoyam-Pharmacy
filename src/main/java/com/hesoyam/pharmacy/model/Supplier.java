/***********************************************************************
 * Module:  Supplier.java
 * Author:  Geri
 * Purpose: Defines the Class Supplier
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public class Supplier extends User {
   private java.util.List<Offer> offer;

   public java.util.List<Offer> getOffer() {
      if (offer == null)
         offer = new java.util.ArrayList<Offer>();
      return offer;
   }

   public java.util.Iterator getIteratorOffer() {
      if (offer == null)
         offer = new java.util.ArrayList<Offer>();
      return offer.iterator();
   }

   public void setOffer(java.util.List<Offer> newOffer) {
      removeAllOffer();
      for (java.util.Iterator iter = newOffer.iterator(); iter.hasNext();)
         addOffer((Offer)iter.next());
   }

   public void addOffer(Offer newOffer) {
      if (newOffer == null)
         return;
      if (this.offer == null)
         this.offer = new java.util.ArrayList<Offer>();
      if (!this.offer.contains(newOffer))
      {
         this.offer.add(newOffer);
         newOffer.setSupplier(this);      
      }
   }

   public void removeOffer(Offer oldOffer) {
      if (oldOffer == null)
         return;
      if (this.offer != null)
         if (this.offer.contains(oldOffer))
         {
            this.offer.remove(oldOffer);
            oldOffer.setSupplier((Supplier)null);
         }
   }

   public void removeAllOffer() {
      if (offer != null)
      {
         Offer oldOffer;
         for (java.util.Iterator iter = getIteratorOffer(); iter.hasNext();)
         {
            oldOffer = (Offer)iter.next();
            iter.remove();
            oldOffer.setSupplier((Supplier)null);
         }
      }
   }

}