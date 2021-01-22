/***********************************************************************
 * Module:  Reply.java
 * Author:  vule
 * Purpose: Defines the Class Reply
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.SysAdmin;

import javax.persistence.*;

@Entity
public class Reply {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 400, nullable = false)
   private String text;

   @ManyToOne(fetch = FetchType.LAZY, optional = true)
   @JoinColumn(name="sys_admin_id", nullable = false)
   private SysAdmin sysAdmin;

}