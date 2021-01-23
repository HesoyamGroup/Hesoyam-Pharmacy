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

   @Column(name="role_name")
   String roleName;

   @Override
   public String getAuthority() {
      return roleName;
   }

   public String getRoleName() {
      return roleName;
   }

   public void setRoleName(String roleName) {
      this.roleName = roleName;
   }

   @JsonIgnore
   public Long getId(){
      return id;
   }

   public void setId(Long id){
      this.id = id;
   }

}