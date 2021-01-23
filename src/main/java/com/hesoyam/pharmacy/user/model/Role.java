/***********************************************************************
 * Module:  Role.java
 * Author:  WIN 10
 * Purpose: Defines the Class Role
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

   @Id
   @Column(name="id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

   @Column(name="name")
   String name;

   @Override
   public String getAuthority() {
      return null;
   }

   public void setName(String name){
      this.name = name;
   }

   @JsonIgnore
   public String getName(){
      return name;
   }

   @JsonIgnore
   public Long getId(){
      return id;
   }

   public void setId(Long id){
      this.id = id;
   }

}