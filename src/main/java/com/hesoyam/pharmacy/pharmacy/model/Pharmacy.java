/***********************************************************************
 * Module:  Pharmacy.java
 * Author:  WIN 10
 * Purpose: Defines the Class Pharmacy
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.appointment.model.Appointment;
import com.hesoyam.pharmacy.finance.model.PriceList;
import com.hesoyam.pharmacy.finance.model.Sale;
import com.hesoyam.pharmacy.location.model.Address;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Pharmacist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Pharmacy {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 75)
   private String name;

   @Column(length = 300)
   private String description;

   @Column
   private float rating;

   @OneToMany(mappedBy="pharmacy", fetch = FetchType.LAZY)
   private List<Pharmacist> pharmacists;

   @ManyToMany
   @JoinTable(name="pharmacies_dermatologists", joinColumns = @JoinColumn(name="pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="dermatologist_id", referencedColumnName = "id"))
   private List<Dermatologist> dermatologists;

   @Embedded
   private Address address;

   @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY)
   private List<Administrator> administrator;

   @OneToOne(optional = true)
   @JoinColumn(name="inventory_id", referencedColumnName = "id")
   private Inventory inventory;

   @OneToOne(fetch=FetchType.LAZY, optional = true)
   @JoinColumn(name="pharmacy_id", referencedColumnName = "id", nullable = false)
   private PriceList priceList;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "pharmacy")
   private List<Sale> sales;

   public List<Pharmacist> getPharmacists() {
      if (pharmacists == null)
         pharmacists = new ArrayList<Pharmacist>();
      return pharmacists;
   }

   public Iterator getIteratorPharmacist() {
      if (pharmacists == null)
         pharmacists = new ArrayList<Pharmacist>();
      return pharmacists.iterator();
   }

   public void setPharmacists(List<Pharmacist> newPharmacist) {
      removeAllPharmacist();
      for (Iterator iter = newPharmacist.iterator(); iter.hasNext();)
         addPharmacist((Pharmacist)iter.next());
   }

   public void addPharmacist(Pharmacist newPharmacist) {
      if (newPharmacist == null)
         return;
      if (this.pharmacists == null)
         this.pharmacists = new ArrayList<Pharmacist>();
      if (!this.pharmacists.contains(newPharmacist))
      {
         this.pharmacists.add(newPharmacist);
         newPharmacist.setPharmacy(this);
      }
   }

   public void removePharmacist(Pharmacist oldPharmacist) {
      if (oldPharmacist == null)
         return;
      if (this.pharmacists != null)
         if (this.pharmacists.contains(oldPharmacist))
         {
            this.pharmacists.remove(oldPharmacist);
            oldPharmacist.setPharmacy((Pharmacy)null);
         }
   }

   public void removeAllPharmacist() {
      if (pharmacists != null)
      {
         Pharmacist oldPharmacist;
         for (Iterator iter = getIteratorPharmacist(); iter.hasNext();)
         {
            oldPharmacist = (Pharmacist)iter.next();
            iter.remove();
            oldPharmacist.setPharmacy((Pharmacy)null);
         }
      }
   }
   public List<Dermatologist> getDermatologists() {
      if (dermatologists == null)
         dermatologists = new ArrayList<Dermatologist>();
      return dermatologists;
   }

   public Iterator getIteratorDermatologist() {
      if (dermatologists == null)
         dermatologists = new ArrayList<Dermatologist>();
      return dermatologists.iterator();
   }

   public void setDermatologists(List<Dermatologist> newDermatologist) {
      removeAllDermatologist();
      for (Iterator iter = newDermatologist.iterator(); iter.hasNext();)
         addDermatologist((Dermatologist)iter.next());
   }

   public void addDermatologist(Dermatologist newDermatologist) {
      if (newDermatologist == null)
         return;
      if (this.dermatologists == null)
         this.dermatologists = new ArrayList<Dermatologist>();
      if (!this.dermatologists.contains(newDermatologist))
      {
         this.dermatologists.add(newDermatologist);
         newDermatologist.addPharmacy(this);
      }
   }

   public void removeDermatologist(Dermatologist oldDermatologist) {
      if (oldDermatologist == null)
         return;
      if (this.dermatologists != null)
         if (this.dermatologists.contains(oldDermatologist))
         {
            this.dermatologists.remove(oldDermatologist);
            oldDermatologist.removePharmacy(this);
         }
   }
   public void removeAllDermatologist() {
      if (dermatologists != null)
      {
         Dermatologist oldDermatologist;
         for (Iterator iter = getIteratorDermatologist(); iter.hasNext();)
         {
            oldDermatologist = (Dermatologist)iter.next();
            iter.remove();
            oldDermatologist.removePharmacy(this);
         }
      }
   }
   public List<Administrator> getAdministrator() {
      if (administrator == null)
         administrator = new ArrayList<Administrator>();
      return administrator;
   }

   public Iterator getIteratorAdministrator() {
      if (administrator == null)
         administrator = new ArrayList<Administrator>();
      return administrator.iterator();
   }

   public void setAdministrator(List<Administrator> newAdministrator) {
      removeAllAdministrator();
      for (Iterator iter = newAdministrator.iterator(); iter.hasNext();)
         addAdministrator((Administrator)iter.next());
   }

   public void addAdministrator(Administrator newAdministrator) {
      if (newAdministrator == null)
         return;
      if (this.administrator == null)
         this.administrator = new ArrayList<Administrator>();
      if (!this.administrator.contains(newAdministrator))
      {
         this.administrator.add(newAdministrator);
         newAdministrator.setPharmacy(this);
      }
   }

   public void removeAdministrator(Administrator oldAdministrator) {
      if (oldAdministrator == null)
         return;
      if (this.administrator != null)
         if (this.administrator.contains(oldAdministrator))
         {
            this.administrator.remove(oldAdministrator);
            oldAdministrator.setPharmacy((Pharmacy)null);
         }
   }
   public void removeAllAdministrator() {
      if (administrator != null)
      {
         Administrator oldAdministrator;
         for (Iterator iter = getIteratorAdministrator(); iter.hasNext();)
         {
            oldAdministrator = (Administrator)iter.next();
            iter.remove();
            oldAdministrator.setPharmacy((Pharmacy)null);
         }
      }
   }
}