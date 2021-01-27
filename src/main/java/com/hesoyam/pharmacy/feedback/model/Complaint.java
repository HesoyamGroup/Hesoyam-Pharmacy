/***********************************************************************
 * Module:  Complaint.java
 * Author:  vule
 * Purpose: Defines the Class Complaint
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.User;
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
   @JoinColumn(name="user_id", nullable = false)
   @NotNull(message = "User who placed a complaint must be provided.")
   protected User user;

   @Enumerated(EnumType.STRING)
   @NotNull(message = "Complaint status must be set.")
   protected ComplaintStatus complaintStatus;

   @OneToOne(fetch = FetchType.LAZY, optional = true)
   protected Reply reply;
}