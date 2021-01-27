/***********************************************************************
 * Module:  Complaint.java
 * Author:  vule
 * Purpose: Defines the Class Complaint
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.Patient;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Complaint {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;

   @Column(length = 400, nullable = false)
   @NotNull
   @Length(min=10, max=400, message = "Complaint content length must be between 10 and 40 characters.")
   protected String body;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="patient_id", nullable = false)
   @NotNull(message = "Patient who placed a complaint must be provided.")
   protected Patient patient;

   @Enumerated(EnumType.STRING)
   @NotNull(message = "Complaint status must be set.")
   protected ComplaintStatus complaintStatus;

   @OneToOne(fetch = FetchType.LAZY, optional = true)
   protected Reply reply;

   protected Complaint(){}

   protected Complaint(String body, Patient patient, ComplaintStatus complaintStatus){
      this.body = body;
      this.patient = patient;
      this.complaintStatus = complaintStatus;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getBody() {
      return body;
   }

   public void setBody(String body) {
      this.body = body;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public ComplaintStatus getComplaintStatus() {
      return complaintStatus;
   }

   public void setComplaintStatus(ComplaintStatus complaintStatus) {
      this.complaintStatus = complaintStatus;
   }

   public Reply getReply() {
      return reply;
   }

   public void setReply(Reply reply) {
      this.reply = reply;
   }
}