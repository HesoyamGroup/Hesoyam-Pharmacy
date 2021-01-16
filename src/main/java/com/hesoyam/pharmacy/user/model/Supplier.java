/***********************************************************************
 * Module:  Supplier.java
 * Author:  Geri
 * Purpose: Defines the Class Supplier
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.hesoyam.pharmacy.finance.model.Offer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Supplier extends User {
   private List<Offer> offers;

   public List<Offer> getOffers() {
      if (offers == null)
         offers = new ArrayList<Offer>();
      return offers;
   }

   public Iterator getIteratorOffer() {
      if (offers == null)
         offers = new ArrayList<Offer>();
      return offers.iterator();
   }

   public void setOffers(List<Offer> newOffer) {
      removeAllOffer();
      for (Iterator iter = newOffer.iterator(); iter.hasNext();)
         addOffer((Offer)iter.next());
   }

   public void addOffer(Offer newOffer) {
      if (newOffer == null)
         return;
      if (this.offers == null)
         this.offers = new ArrayList<Offer>();
      if (!this.offers.contains(newOffer))
      {
         this.offers.add(newOffer);
         newOffer.setSupplier(this);      
      }
   }

   public void removeOffer(Offer oldOffer) {
      if (oldOffer == null)
         return;
      if (this.offers != null)
         if (this.offers.contains(oldOffer))
         {
            this.offers.remove(oldOffer);
            oldOffer.setSupplier((Supplier)null);
         }
   }

   public void removeAllOffer() {
      if (offers != null)
      {
         Offer oldOffer;
         for (Iterator iter = getIteratorOffer(); iter.hasNext();)
         {
            oldOffer = (Offer)iter.next();
            iter.remove();
            oldOffer.setSupplier((Supplier)null);
         }
      }
   }

}