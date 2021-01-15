/***********************************************************************
 * Module:  Pharmacy.java
 * Author:  WIN 10
 * Purpose: Defines the Class Pharmacy
 ***********************************************************************/
package com.hesoyam.pharmacy.model;

public class Pharmacy {
   private Long pharmacyId;
   private String name;
   private String description;
   private float rating;
   
   private java.util.List<Pharmacist> pharmacist;
   private java.util.List<Dermatologist> dermatologist;
   private Address address;
   private java.util.List<Administrator> administrator;
   private Inventory inventory;
   private java.util.List<Appointment> appointment;

   private PriceList priceList;
   private Sale[] sale;

   public java.util.List<Pharmacist> getPharmacist() {
      if (pharmacist == null)
         pharmacist = new java.util.ArrayList<Pharmacist>();
      return pharmacist;
   }

   public java.util.Iterator getIteratorPharmacist() {
      if (pharmacist == null)
         pharmacist = new java.util.ArrayList<Pharmacist>();
      return pharmacist.iterator();
   }

   public void setPharmacist(java.util.List<Pharmacist> newPharmacist) {
      removeAllPharmacist();
      for (java.util.Iterator iter = newPharmacist.iterator(); iter.hasNext();)
         addPharmacist((Pharmacist)iter.next());
   }

   public void addPharmacist(Pharmacist newPharmacist) {
      if (newPharmacist == null)
         return;
      if (this.pharmacist == null)
         this.pharmacist = new java.util.ArrayList<Pharmacist>();
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
         for (java.util.Iterator iter = getIteratorPharmacist(); iter.hasNext();)
         {
            oldPharmacist = (Pharmacist)iter.next();
            iter.remove();
            oldPharmacist.setPharmacy((Pharmacy)null);
         }
      }
   }
   public java.util.List<Dermatologist> getDermatologist() {
      if (dermatologist == null)
         dermatologist = new java.util.ArrayList<Dermatologist>();
      return dermatologist;
   }

   public java.util.Iterator getIteratorDermatologist() {
      if (dermatologist == null)
         dermatologist = new java.util.ArrayList<Dermatologist>();
      return dermatologist.iterator();
   }

   public void setDermatologist(java.util.List<Dermatologist> newDermatologist) {
      removeAllDermatologist();
      for (java.util.Iterator iter = newDermatologist.iterator(); iter.hasNext();)
         addDermatologist((Dermatologist)iter.next());
   }

   public void addDermatologist(Dermatologist newDermatologist) {
      if (newDermatologist == null)
         return;
      if (this.dermatologist == null)
         this.dermatologist = new java.util.ArrayList<Dermatologist>();
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
         for (java.util.Iterator iter = getIteratorDermatologist(); iter.hasNext();)
         {
            oldDermatologist = (Dermatologist)iter.next();
            iter.remove();
            oldDermatologist.removePharmacy(this);
         }
      }
   }
   public java.util.List<Administrator> getAdministrator() {
      if (administrator == null)
         administrator = new java.util.ArrayList<Administrator>();
      return administrator;
   }

   public java.util.Iterator getIteratorAdministrator() {
      if (administrator == null)
         administrator = new java.util.ArrayList<Administrator>();
      return administrator.iterator();
   }

   public void setAdministrator(java.util.List<Administrator> newAdministrator) {
      removeAllAdministrator();
      for (java.util.Iterator iter = newAdministrator.iterator(); iter.hasNext();)
         addAdministrator((Administrator)iter.next());
   }

   public void addAdministrator(Administrator newAdministrator) {
      if (newAdministrator == null)
         return;
      if (this.administrator == null)
         this.administrator = new java.util.ArrayList<Administrator>();
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
         for (java.util.Iterator iter = getIteratorAdministrator(); iter.hasNext();)
         {
            oldAdministrator = (Administrator)iter.next();
            iter.remove();
            oldAdministrator.setPharmacy((Pharmacy)null);
         }
      }
   }
   public java.util.List<Appointment> getAppointment() {
      if (appointment == null)
         appointment = new java.util.ArrayList<Appointment>();
      return appointment;
   }
   public java.util.Iterator getIteratorAppointment() {
      if (appointment == null)
         appointment = new java.util.ArrayList<Appointment>();
      return appointment.iterator();
   }
   public void setAppointment(java.util.List<Appointment> newAppointment) {
      removeAllAppointment();
      for (java.util.Iterator iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((Appointment)iter.next());
   }

   public void addAppointment(Appointment newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointment == null)
         this.appointment = new java.util.ArrayList<Appointment>();
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
      if (appointment != null)
      {
         Appointment oldAppointment;
         for (java.util.Iterator iter = getIteratorAppointment(); iter.hasNext();)
         {
            oldAppointment = (Appointment)iter.next();
            iter.remove();
            oldAppointment.setPharmacy((Pharmacy)null);
         }
      }
   }
   


}