/***********************************************************************
 * Module:  Complaint.java
 * Author:  vule
 * Purpose: Defines the Class Complaint
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.User;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Complaint {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;

   @Column(length = 400, nullable = false)
   protected String body;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="user_id", nullable = false)
   protected User user;

   @Enumerated(EnumType.STRING)
   protected ComplaintStatus complaintStatus;

   @OneToOne(fetch = FetchType.LAZY, optional = true)
   protected Reply reply;
}