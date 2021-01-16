/***********************************************************************
 * Module:  Therapy.java
 * Author:  WIN 10
 * Purpose: Defines the Class Therapy
 ***********************************************************************/
package com.hesoyam.pharmacy.appointment.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Therapy {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name="therapy_id", referencedColumnName="id")
   private List<TherapyItem> therapyItems;

   public List<TherapyItem> getTherapyItems() {
      if (therapyItems == null)
         therapyItems = new ArrayList<TherapyItem>();
      return therapyItems;
   }

   public Iterator getIteratorTherapyItem() {
      if (therapyItems == null)
         therapyItems = new ArrayList<TherapyItem>();
      return therapyItems.iterator();
   }

   public void setTherapyItems(List<TherapyItem> newTherapyItem) {
      removeAllTherapyItem();
      for (Iterator iter = newTherapyItem.iterator(); iter.hasNext();)
         addTherapyItem((TherapyItem)iter.next());
   }

   public void addTherapyItem(TherapyItem newTherapyItem) {
      if (newTherapyItem == null)
         return;
      if (this.therapyItems == null)
         this.therapyItems = new ArrayList<TherapyItem>();
      if (!this.therapyItems.contains(newTherapyItem))
         this.therapyItems.add(newTherapyItem);
   }

   public void removeTherapyItem(TherapyItem oldTherapyItem) {
      if (oldTherapyItem == null)
         return;
      if (this.therapyItems != null)
         if (this.therapyItems.contains(oldTherapyItem))
            this.therapyItems.remove(oldTherapyItem);
   }

   public void removeAllTherapyItem() {
      if (therapyItems != null)
         therapyItems.clear();
   }

}