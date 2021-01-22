/*
 Module:  Supplier.java
 Author:  Geri
 Purpose: Defines the Class Supplier
 */
package com.hesoyam.pharmacy.user.model;

import com.hesoyam.pharmacy.pharmacy.model.Offer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Supplier extends User {

   @OneToMany(fetch = FetchType.LAZY, mappedBy="supplier")
   private List<Offer> offers;

   public List<Offer> getOffers() {
      if (offers == null)
         offers = new ArrayList<>();
      return offers;
   }

   public Iterator<Offer> getIteratorOffer() {
      if (offers == null)
         offers = new ArrayList<>();
      return offers.iterator();
   }

   public void setOffers(List<Offer> newOffer) {
      removeAllOffer();
      for (Iterator<Offer> iter = newOffer.iterator(); iter.hasNext();)
         addOffer(iter.next());
   }

   public void addOffer(Offer newOffer) {
      if (newOffer == null)
         return;
      if (this.offers == null)
         this.offers = new ArrayList<>();
      if (!this.offers.contains(newOffer))
      {
         this.offers.add(newOffer);
         newOffer.setSupplier(this);      
      }
   }

   public void removeOffer(Offer oldOffer) {
      if (oldOffer == null)
         return;
      if (this.offers != null && this.offers.contains(oldOffer)) {
         this.offers.remove(oldOffer);
         oldOffer.setSupplier((Supplier)null);
      }
   }

   public void removeAllOffer() {
      if (offers != null)
      {
         Offer oldOffer;
         for (Iterator<Offer> iter = getIteratorOffer(); iter.hasNext();)
         {
            oldOffer = iter.next();
            iter.remove();
            oldOffer.setSupplier((Supplier)null);
         }
      }
   }

}