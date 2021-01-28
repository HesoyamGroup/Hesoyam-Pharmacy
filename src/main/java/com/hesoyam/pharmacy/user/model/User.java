/***********************************************************************
 * Module:  User.java
 * Author:  vule
 * Purpose: Defines the Class User
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hesoyam.pharmacy.location.model.Address;
import com.hesoyam.pharmacy.user.validators.PhoneNumberConstraint;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;

   @Column(name="enabled")
   protected boolean enabled;

   @Column
   protected boolean passwordReset;


   @Column(length=75, name = "first_name")
   @NotNull(message = "First name must be provided.")
   @Length(min=2, max=75, message= "First name length should be between 2 and 75 characters.")
   protected String firstName;

   @Column(length=100, name="last_name")
   @NotNull(message = "Last name must be provided.")
   @Length(min=3, max = 100, message = "Last name length should be between 3 and 100 characters.")
   protected String lastName;

   @Column(length=20)
   @PhoneNumberConstraint
   protected String telephone;

   @Column(length=75, unique = true)
   @NotBlank
   @Email(message = "A valid email address must be provided.")
   protected String email;

   @Enumerated(EnumType.STRING)
   @NotNull
   protected Gender gender;

   @Enumerated(EnumType.STRING)
   @NotNull
   protected RoleEnum roleEnum;

   @Column(length=200)
   @NotNull
   @Length(min=8, max=200) //NOTE: Max Length due to JWT length
   protected String password;

   @Column(name = "last_password_reset_date")
   @NotNull
   private Timestamp lastPasswordResetDate;

   @Embedded
   protected Address address;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "user_roles",
           joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
   private List<Role> authorities;

   protected User(){}

   public void update(User user){
      //Everything copied except user ID
      this.enabled = user.isEnabled();
      this.firstName = user.getFirstName();
      this.lastName = user.getLastName();
      this.telephone = user.getTelephone();
      this.email = user.getEmail();
      this.gender = user.getGender();
      this.roleEnum = user.getRoleEnum();
      if(!this.password.equals(user.getPassword())){
         //If a password has been changed, update timestamp
         lastPasswordResetDate = new Timestamp((new Date()).getTime());
      }else{
         lastPasswordResetDate = user.getLastPasswordResetDate();
      }
      this.password = user.getPassword();
      this.address = user.getAddress();
      this.authorities = user.authorities;
   }

   public void setAuthorities(List<Role> authorities) {
      this.authorities = authorities;
   }
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return this.authorities;
   }

   public Role getUserRole(){
      if(!authorities.isEmpty())
         return authorities.get(0);

      return null;
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
      if(this.password != null && !this.password.equals(password)){
         //If a password has been changed, update timestamp
         lastPasswordResetDate = new Timestamp((new Date()).getTime());
      }
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
      //TODO: REPLACE WITH this.enabled!!!
      return true;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public boolean isPasswordReset() {
      return passwordReset;
   }

   public void setPasswordReset(boolean passwordReset) {
      this.passwordReset = passwordReset;
   }
   
   public RoleEnum getRoleEnum() {
      return roleEnum;
   }

   public void setRoleEnum(RoleEnum roleEnum) {
      this.roleEnum = roleEnum;
   }
}