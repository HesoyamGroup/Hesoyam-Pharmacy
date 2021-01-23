/***********************************************************************
 * Module:  User.java
 * Author:  vule
 * Purpose: Defines the Class User
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hesoyam.pharmacy.location.model.Address;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;

   @Column(length=100, name = "first_name")
   protected String firstName;

   @Column(length=100, name="last_name")
   protected String lastName;

   @Column(length=20)
   protected String telephone;

   @Column(length=50, unique = true)
   protected String email;

   @Enumerated(EnumType.STRING)
   protected Gender gender;

   //TODO: Password encrypt (Spring Security)
   @Column(length=75)
   protected String password;

   @Column(name = "last_password_reset_date")
   private Timestamp lastPasswordResetDate;

   @Embedded
   protected Address address;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "user_roles",
           joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
   private List<Role> roles;


   public void setRoles(List<Role> roles) {
      this.roles = roles;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return this.roles;
   }

   public Long getId() {
      return id;
   }

   public User setId(Long id) {
      this.id = id;
      return this;
   }

   public String getFirstName() {
      return firstName;
   }

   public User setFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public String getLastName() {
      return lastName;
   }

   public User setLastName(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public String getTelephone() {
      return telephone;
   }

   public User setTelephone(String telephone) {
      this.telephone = telephone;
      return this;
   }

   //UserDetails uses username for auth, not email.
   @Override
   public String getUsername(){
      return getEmail();
   }

   public String getEmail() {
      return email;
   }

   public User setEmail(String email) {
      this.email = email;
      return this;
   }

   public Gender getGender() {
      return gender;
   }

   public User setGender(Gender gender) {
      this.gender = gender;
      return this;
   }

   @Override
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Timestamp getLastPasswordResetDate() {
      return lastPasswordResetDate;
   }

   public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
      this.lastPasswordResetDate = lastPasswordResetDate;
   }

   public Address getAddress() {
      return address;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   @JsonIgnore
   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @JsonIgnore
   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @JsonIgnore
   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}