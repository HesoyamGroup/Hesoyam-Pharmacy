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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pharmacy {
   private Long pharmacyId;
   private String name;
   private String description;
   private float rating;
   
   private List<Pharmacist> pharmacist;
   private List<Dermatologist> dermatologist;
   private Address address;
   private List<Administrator> administrator;
   private Inventory inventory;
   private List<Appointment> appointment;

   private PriceList priceList;
   private List<Sale> sales;

   public List<Pharmacist> getPharmacist() {
      if (pharmacist == null)
         pharmacist = new ArrayList<Pharmacist>();
      return pharmacist;
   }

   public Iterator getIteratorPharmacist() {
      if (pharmacist == null)
         pharmacist = new ArrayList<Pharmacist>();
      return pharmacist.iterator();
   }

   public void setPharmacist(List<Pharmacist> newPharmacist) {
      removeAllPharmacist();
      for (Iterator iter = newPharmacist.iterator(); iter.hasNext();)
         addPharmacist((Pharmacist)iter.next());
   }

   public void addPharmacist(Pharmacist newPharmacist) {
      if (newPharmacist == null)
         return;
      if (this.pharmacist == null)
         this.pharmacist = new ArrayList<Pharmacist>();
      if (!this.pharmacist.contains(newPharmacist))
      {
         this.pharmacist.add(newPharmacist);
         newPharmacist.setPharmacy(this);      
      }
   }

   public void removePharmacist(Pharmacist oldPharmacist) {
      if (oldPharmacist == null)
         return;
      if (this.pharmacist != null)
         if (this.pharmacist.contains(oldPharmacist))
         {
            this.pharmacist.remove(oldPharmacist);
            oldPharmacist.setPharmacy((Pharmacy)null);
         }
   }

   public void removeAllPharmacist() {
      if (pharmacist != null)
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
   public List<Dermatologist> getDermatologist() {
      if (dermatologist == null)
         dermatologist = new ArrayList<Dermatologist>();
      return dermatologist;
   }

   public Iterator getIteratorDermatologist() {
      if (dermatologist == null)
         dermatologist = new ArrayList<Dermatologist>();
      return dermatologist.iterator();
   }

   public void setDermatologist(List<Dermatologist> newDermatologist) {
      removeAllDermatologist();
      for (Iterator iter = newDermatologist.iterator(); iter.hasNext();)
         addDermatologist((Dermatologist)iter.next());
   }

   public void addDermatologist(Dermatologist newDermatologist) {
      if (newDermatologist == null)
         return;
      if (this.dermatologist == null)
         this.dermatologist = new ArrayList<Dermatologist>();
      if (!this.dermatologist.contains(newDermatologist))
      {
         this.dermatologist.add(newDermatologist);
         newDermatologist.addPharmacy(this);      
      }
   }

   public void removeDermatologist(Dermatologist oldDermatologist) {
      if (oldDermatologist == null)
         return;
      if (this.dermatologist != null)
         if (this.dermatologist.contains(oldDermatologist))
         {
            this.dermatologist.remove(oldDermatologist);
            oldDermatologist.removePharmacy(this);
         }
   }
   public void removeAllDermatologist() {
      if (dermatologist != null)
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
   public List<Appointment> getAppointment() {
      if (appointment == null)
         appointment = new ArrayList<Appointment>();
      return appointment;
   }
   public Iterator getIteratorAppointment() {
      if (appointment == null)
         appointment = new ArrayList<Appointment>();
      return appointment.iterator();
   }
   public void setAppointment(List<Appointment> newAppointment) {
      removeAllAppointment();
      for (Iterator iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((Appointment)iter.next());
   }

   public void addAppointment(Appointment newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointment == null)
         this.appointment = new ArrayList<Appointment>();
      if (!this.appointment.contains(newAppointment))
      {
         this.appointment.add(newAppointment);
         newAppointment.setPharmacy(this);      
      }
   }
   public void removeAppointment(Appointment oldAppointment) {
      if (oldAppointment == null)
         return;
      if (this.appointment != null)
         if (this.appointment.contains(oldAppointment))
         {
            this.appointment.remove(oldAppointment);
            oldAppointment.setPharmacy((Pharmacy)null);
         }
   }
   public void removeAllAppointment() {
      if (appointment != null) {
         Appointment oldAppointment;
         for (Iterator iter = getIteratorAppointment(); iter.hasNext(); ) {
            oldAppointment = (Appointment) iter.next();
            iter.remove();
            oldAppointment.setPharmacy((Pharmacy) null);
         }
      }
   }
}