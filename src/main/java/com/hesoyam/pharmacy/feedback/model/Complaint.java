/***********************************************************************
 * Module:  Complaint.java
 * Author:  vule
 * Purpose: Defines the Class Complaint
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Complaint {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;

   @Column(length = 400, nullable = false)
   protected String text;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="user_id", nullable = false)
   protected User user;

   @Enumerated(EnumType.STRING)
   protected ComplaintStatus complaintStatus;

   @OneToOne(fetch = FetchType.LAZY, optional = true)
   @JoinColumn(name="reply_id", nullable = true)
   protected Reply reply;
}