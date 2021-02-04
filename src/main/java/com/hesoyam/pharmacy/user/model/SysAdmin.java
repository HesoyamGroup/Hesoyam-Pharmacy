/***********************************************************************
 * Module:  SysAdmin.java
 * Author:  vule
 * Purpose: Defines the Class SysAdmin
 ***********************************************************************/
package com.hesoyam.pharmacy.user.model;

import javax.persistence.Entity;

@Entity
public class SysAdmin extends User {

    @Override
    public boolean isEnabled() {
        return true;
    }
}