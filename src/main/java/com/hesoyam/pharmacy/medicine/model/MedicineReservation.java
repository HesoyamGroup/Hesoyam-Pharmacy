/***********************************************************************
 * Module:  MedicineReservation.java
 * Author:  WIN 10
 * Purpose: Defines the Class MedicineReservation
 ***********************************************************************/
package com.hesoyam.pharmacy.medicine.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Patient;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class MedicineReservation {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   @JsonSerialize(using = LocalDateTimeSerializer.class)
   @JsonDeserialize(using = LocalDateTimeDeserializer.class)
   @Column(name = "pick_up_date")
   @NotNull(message = "Pick up date must be specified.")
   private LocalDateTime pickUpDate;

   @Column(length =  50)
   @NotNull(message = "Medicine reservation code must be provided.")
   @Length(min=1, max=50, message = "Medicine reservation code length should be between 1 and 50.")
   private String code;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "medicine_reservation_id", referencedColumnName = "id")
   private List<MedicineReservationItem> medicineReservationItems;

   @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
   @JoinColumn(name = "patient_id", nullable = false)
   private Patient patient;

   @Enumerated(EnumType.STRING)
   private MedicineReservationStatus medicineReservationStatus;

//   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "pharmacy_id", nullable = false)
   private Pharmacy pharmacy;

   @Version
   @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
   private Long version = 0l;

   public Long getVersion() {
      return version;
   }

   public void setVersion(Long version) {
      this.version = version;
   }

   /*public MedicineReservation(Long id, @NotNull(message = "Pick up date must be specified.") LocalDateTime pickUpDate, @NotNull(message = "Medicine reservation code must be provided.") @Length(min = 1, max = 50, message = "Medicine reservation code length should be between 1 and 50.") String code, List<MedicineReservationItem> medicineReservationItems, Patient patient, MedicineReservationStatus medicineReservationStatus) {
      this.id = id;
      this.pickUpDate = pickUpDate;
      this.code = code;
      this.medicineReservationItems = medicineReservationItems;
      this.patient = patient;
      this.medicineReservationStatus = medicineReservationStatus;
   }*/

   public MedicineReservation(Long id, LocalDateTime pickUpDate, @Length(min = 1, max = 50, message = "Medicine reservation code length should be between 1 and 50.") String code, List<MedicineReservationItem> medicineReservationItems, Patient patient, MedicineReservationStatus medicineReservationStatus) {
      this.id = id;
      this.pickUpDate = pickUpDate;
      this.code = code;
      this.medicineReservationItems = medicineReservationItems;
      this.patient = patient;
      this.medicineReservationStatus = medicineReservationStatus;
   }

   public MedicineReservation() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalDateTime getPickUpDate() {
      return pickUpDate;
   }

   public void setPickUpDate(LocalDateTime pickUpDate) {
      this.pickUpDate = pickUpDate;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public MedicineReservationStatus getMedicineReservationStatus() {
      return medicineReservationStatus;
   }

   public void setMedicineReservationStatus(MedicineReservationStatus medicineReservationStatus) {
      this.medicineReservationStatus = medicineReservationStatus;
   }

   public List<MedicineReservationItem> getMedicineReservationItems() {
      if (medicineReservationItems == null)
         medicineReservationItems = new ArrayList<>();
      return medicineReservationItems;
   }

   public Iterator<MedicineReservationItem> getIteratorMedicineReservationItem() {
      if (medicineReservationItems == null)
         medicineReservationItems = new ArrayList<>();
      return medicineReservationItems.iterator();
   }

   public void setMedicineReservationItems(List<MedicineReservationItem> newMedicineReservationItem) {
      removeAllMedicineReservationItem();
      for (Iterator<MedicineReservationItem> iter = newMedicineReservationItem.iterator(); iter.hasNext();)
         addMedicineReservationItem(iter.next());
   }

   public void addMedicineReservationItem(MedicineReservationItem newMedicineReservationItem) {
      if (newMedicineReservationItem == null)
         return;
      if (this.medicineReservationItems == null)
         this.medicineReservationItems = new ArrayList<>();
      if (!this.medicineReservationItems.contains(newMedicineReservationItem))
         this.medicineReservationItems.add(newMedicineReservationItem);
   }

   public void removeMedicineReservationItem(MedicineReservationItem oldMedicineReservationItem) {
      if (oldMedicineReservationItem == null)
         return;
      if (this.medicineReservationItems != null && this.medicineReservationItems.contains(oldMedicineReservationItem))
            this.medicineReservationItems.remove(oldMedicineReservationItem);
   }

   public void removeAllMedicineReservationItem() {
      if (medicineReservationItems != null)
         medicineReservationItems.clear();
   }

   public void update(MedicineReservation medicineReservation){
      this.id = medicineReservation.getId();
      this.code = medicineReservation.getCode();
      this.medicineReservationItems = medicineReservation.getMedicineReservationItems();
      this.medicineReservationStatus = medicineReservation.getMedicineReservationStatus();
      this.patient = medicineReservation.getPatient();
      this.pickUpDate = medicineReservation.getPickUpDate();
   }

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }
}