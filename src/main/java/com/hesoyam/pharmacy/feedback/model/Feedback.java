/***********************************************************************
 * Module:  Feedback.java
 * Author:  WIN 10
 * Purpose: Defines the Class Feedback
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.Patient;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Feedback {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;

   @Column(length = 400, nullable = false)
   protected String comment;

   @Column(nullable = false)
   protected int rating;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "patient_id", nullable = false)
   protected Patient patient;

}