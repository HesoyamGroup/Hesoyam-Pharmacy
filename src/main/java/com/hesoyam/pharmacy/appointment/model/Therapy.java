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
   @JoinColumn(name="therapy_id", referencedColumnName="id", nullable = false)
   private List<TherapyItem> therapyItems;


   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public List<TherapyItem> getTherapyItems() {
      if (therapyItems == null)
         therapyItems = new ArrayList<>();
      return therapyItems;
   }

   public Iterator<TherapyItem> getIteratorTherapyItem() {
      if (therapyItems == null)
         therapyItems = new ArrayList<>();
      return therapyItems.iterator();
   }

   public void setTherapyItems(List<TherapyItem> newTherapyItem) {
      removeAllTherapyItem();
      for (Iterator<TherapyItem> iter = newTherapyItem.iterator(); iter.hasNext();)
         addTherapyItem(iter.next());
   }

   public void addTherapyItem(TherapyItem newTherapyItem) {
      if (newTherapyItem == null)
         return;
      if (this.therapyItems == null)
         this.therapyItems = new ArrayList<>();
      if (!this.therapyItems.contains(newTherapyItem))
         this.therapyItems.add(newTherapyItem);
   }

   public void removeTherapyItem(TherapyItem oldTherapyItem) {
      if (oldTherapyItem == null)
         return;
      if (this.therapyItems != null && this.therapyItems.contains(oldTherapyItem)){
            this.therapyItems.remove(oldTherapyItem);
      }
   }

   public void removeAllTherapyItem() {
      if (therapyItems != null)
         therapyItems.clear();
   }

}