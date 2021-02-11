/***********************************************************************
 * Module:  Reply.java
 * Author:  vule
 * Purpose: Defines the Class Reply
 ***********************************************************************/
package com.hesoyam.pharmacy.feedback.model;

import com.hesoyam.pharmacy.user.model.SysAdmin;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Reply {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Version
   @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
   private Integer version;

   @Column(length = 400, nullable = false)
   @NotNull(message = "Reply text must be provided.")
   @Length(min=5, max=400, message = "Reply length should be between 5 and 400 characters.")
   private String text;

   @ManyToOne(fetch = FetchType.LAZY, optional = true)
   @JoinColumn(name="sys_admin_id", nullable = false)
   @NotNull(message = "Reply must have a placer(Sys admin).")
   private SysAdmin sysAdmin;

   public Reply(){}

   public Reply(String text, SysAdmin sysAdmin) {
      this.text = text;
      this.sysAdmin = sysAdmin;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public SysAdmin getSysAdmin() {
      return sysAdmin;
   }

   public void setSysAdmin(SysAdmin sysAdmin) {
      this.sysAdmin = sysAdmin;
   }

   public Integer getVersion() {
      return version;
   }

   public void setVersion(Integer version) {
      this.version = version;
   }
}