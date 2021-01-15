/***********************************************************************
 * Module:  Pharmacy.java
 * Author:  WIN 10
 * Purpose: Defines the Class Pharmacy
 ***********************************************************************/
package com.hesoyam.pharmacy.model;
import java.util.*;

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
   
   
   /** @pdGenerated default getter */
   public java.util.List<Pharmacist> getPharmacist() {
      if (pharmacist == null)
         pharmacist = new java.util.ArrayList<Pharmacist>();
      return pharmacist;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPharmacist() {
      if (pharmacist == null)
         pharmacist = new java.util.ArrayList<Pharmacist>();
      return pharmacist.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPharmacist */
   public void setPharmacist(java.util.List<Pharmacist> newPharmacist) {
      removeAllPharmacist();
      for (java.util.Iterator iter = newPharmacist.iterator(); iter.hasNext();)
         addPharmacist((Pharmacist)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPharmacist */
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
   
   /** @pdGenerated default remove
     * @param oldPharmacist */
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
   
   /** @pdGenerated default removeAll */
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
   /** @pdGenerated default getter */
   public java.util.List<Dermatologist> getDermatologist() {
      if (dermatologist == null)
         dermatologist = new java.util.ArrayList<Dermatologist>();
      return dermatologist;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDermatologist() {
      if (dermatologist == null)
         dermatologist = new java.util.ArrayList<Dermatologist>();
      return dermatologist.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDermatologist */
   public void setDermatologist(java.util.List<Dermatologist> newDermatologist) {
      removeAllDermatologist();
      for (java.util.Iterator iter = newDermatologist.iterator(); iter.hasNext();)
         addDermatologist((Dermatologist)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDermatologist */
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
   
   /** @pdGenerated default remove
     * @param oldDermatologist */
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
   
   /** @pdGenerated default removeAll */
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
   /** @pdGenerated default getter */
   public java.util.List<Administrator> getAdministrator() {
      if (administrator == null)
         administrator = new java.util.ArrayList<Administrator>();
      return administrator;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAdministrator() {
      if (administrator == null)
         administrator = new java.util.ArrayList<Administrator>();
      return administrator.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAdministrator */
   public void setAdministrator(java.util.List<Administrator> newAdministrator) {
      removeAllAdministrator();
      for (java.util.Iterator iter = newAdministrator.iterator(); iter.hasNext();)
         addAdministrator((Administrator)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAdministrator */
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
   
   /** @pdGenerated default remove
     * @param oldAdministrator */
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
   
   /** @pdGenerated default removeAll */
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
   /** @pdGenerated default getter */
   public java.util.List<Appointment> getAppointment() {
      if (appointment == null)
         appointment = new java.util.ArrayList<Appointment>();
      return appointment;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAppointment() {
      if (appointment == null)
         appointment = new java.util.ArrayList<Appointment>();
      return appointment.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAppointment */
   public void setAppointment(java.util.List<Appointment> newAppointment) {
      removeAllAppointment();
      for (java.util.Iterator iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((Appointment)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAppointment */
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
   
   /** @pdGenerated default remove
     * @param oldAppointment */
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
   
   /** @pdGenerated default removeAll */
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
   
   public PriceList priceList;
   public Sale[] sale;

}