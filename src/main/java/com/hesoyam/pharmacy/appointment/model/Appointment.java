/***********************************************************************
 * Module:  Appointment.java
 * Author:  WIN 10
 * Purpose: Defines the Class Appointment
 ***********************************************************************/
package com.hesoyam.pharmacy.appointment.model;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.util.DateTimeRange;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class Appointment {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;

   @Version
   @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
   private Integer version;

   @Column(length = 500)
   @Length(max=500, message = "Report length should not exceed 500 characters.")
   protected String report;

   @Enumerated(EnumType.STRING)
   @NotNull(message = "Appointment status must be set.")
   protected AppointmentStatus appointmentStatus;

   @Embedded
   @Valid
   protected DateTimeRange dateTimeRange;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="pharmacy_id", nullable = false)
   @NotNull(message = "Pharmacy where appointment takes place must be set.")
   protected Pharmacy pharmacy;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="therapy_id")
   protected Therapy therapy;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "patient_id")
   protected Patient patient;

   protected Double price;

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getReport() {
      return report;
   }

   public void setReport(String report) {
      this.report = report;
   }

   public AppointmentStatus getAppointmentStatus() {
      return appointmentStatus;
   }

   public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
      this.appointmentStatus = appointmentStatus;
   }

   public DateTimeRange getDateTimeRange() {
      return dateTimeRange;
   }

   public void setDateTimeRange(DateTimeRange dateTimeRange) {
      this.dateTimeRange = dateTimeRange;
   }

   public Therapy getTherapy() {
      return therapy;
   }

   public void setTherapy(Therapy therapy) {
      this.therapy = therapy;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy newPharmacy) {
      if (this.pharmacy == null || !this.pharmacy.equals(newPharmacy))
      {
         this.pharmacy = newPharmacy;
      }
   }

   public void setVersion(Integer version) {
      this.version = version;
   }

   public boolean isConflictingWith(DateTimeRange range){
      return this.dateTimeRange.overlaps(range);
   }


   public boolean isUpcoming(){
      return this.dateTimeRange.getFrom().isAfter(LocalDateTime.now());
   }

   public boolean isTakeable(){
      return appointmentStatus == AppointmentStatus.FREE;
   }

    public boolean isTaken() {
      return appointmentStatus == AppointmentStatus.TAKEN;
    }
}