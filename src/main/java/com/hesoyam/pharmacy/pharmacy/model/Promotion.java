/***********************************************************************
 * Module:  Promotion.java
 * Author:  WIN 10
 * Purpose: Defines the Class Promotion
 ***********************************************************************/
package com.hesoyam.pharmacy.pharmacy.model;

import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.util.DateTimeRange;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=350)
    @Length(min=1, max=350, message = "Promotion description should be between 1 and 350 characters.")
    private String description;

    @Column(length = 100)
    @Length(min = 1, max = 100, message = "Promotion title should be between 1 and 350 characters.")
    private String title;

    @Embedded
    private DateTimeRange dateTimeRange;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="administrator_id", nullable = false)
    private Administrator administrator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = false)
    private Pharmacy pharmacy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTimeRange getDateTimeRange() {
        return dateTimeRange;
    }

    public void setDateTimeRange(DateTimeRange dateTimeRange) {
        this.dateTimeRange = dateTimeRange;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}