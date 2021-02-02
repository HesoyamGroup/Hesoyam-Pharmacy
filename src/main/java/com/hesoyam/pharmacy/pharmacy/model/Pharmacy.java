/***********************************************************************
 * Module:  Pharmacy.java
 * Author:  WIN 10
 * Purpose: Defines the Class Pharmacy
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hesoyam.pharmacy.location.model.Address;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
public class Pharmacy {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 75)
   @NotNull(message = "Pharmacy name must be provided.")
   @Length(min=2, max=75, message="Pharmacy name length should be between 2 and 75 characters.")
   private String name;

   @Column(length = 300)
   @NotNull
   @Length(max=300, message = "Pharmacy description length should not exceed 300 characters.")
   private String description;

   @Column
   @Min(0)
   private double rating;

   @OneToMany(mappedBy="pharmacy", fetch = FetchType.LAZY)
   @JsonManagedReference
   private List<Pharmacist> pharmacists;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name="pharmacies_dermatologists", joinColumns = @JoinColumn(name="pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="dermatologist_id", referencedColumnName = "id"))
   @JsonManagedReference
   private List<Dermatologist> dermatologists;

   @Embedded
   private Address address;

   @OneToMany(targetEntity = Administrator.class,mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JsonManagedReference
   private List<Administrator> administrator;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "subscription", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"))
   private List<Patient> subscribedPatients;

   @OneToOne(optional = true, mappedBy = "pharmacy", cascade = CascadeType.REMOVE)
   @JsonManagedReference
   private Inventory inventory;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "pharmacy")
   @JsonBackReference
   private List<Sale> sales;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public double getRating() {
      return rating;
   }

   public void setRating(double rating) {
      this.rating = rating;
   }

   public Address getAddress() {
      return address;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   public List<Patient> getSubscribedPatients() {
      return subscribedPatients;
   }

   public void setSubscribedPatients(List<Patient> subscribedPatients) {
      this.subscribedPatients = subscribedPatients;
   }

   public Inventory getInventory() {
      return inventory;
   }

   public void setInventory(Inventory inventory) {
      this.inventory = inventory;
   }

   public List<Sale> getSales() {
      return sales;
   }

   public void setSales(List<Sale> sales) {
      this.sales = sales;
   }

   public List<Pharmacist> getPharmacists() {
      if (pharmacists == null)
         pharmacists = new ArrayList<>();
      return pharmacists;
   }

   public Iterator<Pharmacist> getIteratorPharmacist() {
      if (pharmacists == null)
         pharmacists = new ArrayList<>();
      return pharmacists.iterator();
   }

   public void setPharmacists(List<Pharmacist> newPharmacist) {
      removeAllPharmacist();
      for (Iterator<Pharmacist> iter = newPharmacist.iterator(); iter.hasNext();)
         addPharmacist(iter.next());
   }

   public void addPharmacist(Pharmacist newPharmacist) {
      if (newPharmacist == null)
         return;
      if (this.pharmacists == null)
         this.pharmacists = new ArrayList<>();
      if (!this.pharmacists.contains(newPharmacist))
      {
         this.pharmacists.add(newPharmacist);
         newPharmacist.setPharmacy(this);
      }
   }

   public void removePharmacist(Pharmacist oldPharmacist) {
      if (oldPharmacist == null)
         return;
      if (this.pharmacists != null && this.pharmacists.contains(oldPharmacist))
         {
            this.pharmacists.remove(oldPharmacist);
            oldPharmacist.setPharmacy((Pharmacy)null);
         }
   }

   public void removeAllPharmacist() {
      if (pharmacists != null)
      {
         Pharmacist oldPharmacist;
         for (Iterator<Pharmacist> iter = getIteratorPharmacist(); iter.hasNext();)
         {
            oldPharmacist = iter.next();
            iter.remove();
            oldPharmacist.setPharmacy((Pharmacy)null);
         }
      }
   }
   public List<Dermatologist> getDermatologists() {
      if (dermatologists == null)
         dermatologists = new ArrayList<>();
      return dermatologists;
   }

   public Iterator<Dermatologist> getIteratorDermatologist() {
      if (dermatologists == null)
         dermatologists = new ArrayList<>();
      return dermatologists.iterator();
   }

   public void setDermatologists(List<Dermatologist> newDermatologist) {
      removeAllDermatologist();
      for (Iterator<Dermatologist> iter = newDermatologist.iterator(); iter.hasNext();)
         addDermatologist(iter.next());
   }

   public void addDermatologist(Dermatologist newDermatologist) {
      if (newDermatologist == null)
         return;
      if (this.dermatologists == null)
         this.dermatologists = new ArrayList<>();
      if (!this.dermatologists.contains(newDermatologist))
      {
         this.dermatologists.add(newDermatologist);
         newDermatologist.addPharmacy(this);
      }
   }

   public void removeDermatologist(Dermatologist oldDermatologist) {
      if (oldDermatologist == null)
         return;
      if (this.dermatologists != null && this.dermatologists.contains(oldDermatologist)) {
         this.dermatologists.remove(oldDermatologist);
         oldDermatologist.removePharmacy(this);
      }
   }
   public void removeAllDermatologist() {
      if (dermatologists != null)
      {
         Dermatologist oldDermatologist;
         for (Iterator<Dermatologist> iter = getIteratorDermatologist(); iter.hasNext();)
         {
            oldDermatologist = iter.next();
            iter.remove();
            oldDermatologist.removePharmacy(this);
         }
      }
   }
   public List<Administrator> getAdministrator() {
      if (administrator == null)
         administrator = new ArrayList<>();
      return administrator;
   }

   public Iterator<Administrator> getIteratorAdministrator() {
      if (administrator == null)
         administrator = new ArrayList<>();
      return administrator.iterator();
   }

   public void setAdministrator(List<Administrator> newAdministrator) {
      removeAllAdministrator();
      for (Iterator<Administrator> iter = newAdministrator.iterator(); iter.hasNext();){
         addAdministrator(iter.next());
      }
   }

   public void addAdministrator(Administrator newAdministrator) {
      if (newAdministrator == null)
         return;
      if (this.administrator == null)
         this.administrator = new ArrayList<>();
      if (!this.administrator.contains(newAdministrator))
      {
         this.administrator.add(newAdministrator);
         newAdministrator.setPharmacy(this);
      }
   }

   public void removeAdministrator(Administrator oldAdministrator) {
      if (oldAdministrator == null)
         return;
      if (this.administrator != null && this.administrator.contains(oldAdministrator)) {
         this.administrator.remove(oldAdministrator);
         oldAdministrator.setPharmacy((Pharmacy)null);
      }
   }
   public void removeAllAdministrator() {
      if (administrator != null)
      {
         Administrator oldAdministrator;
         for (Iterator<Administrator> iter = getIteratorAdministrator(); iter.hasNext();)
         {
            oldAdministrator = iter.next();
            iter.remove();
            oldAdministrator.setPharmacy((Pharmacy)null);
         }
      }
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Pharmacy)) return false;
      Pharmacy pharmacy = (Pharmacy) o;
      return getId().equals(pharmacy.getId());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId());
   }
}