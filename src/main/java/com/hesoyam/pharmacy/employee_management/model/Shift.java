/***********************************************************************
 * Module:  Shift.java
 * Author:  vule
 * Purpose: Defines the Class Shift
 ***********************************************************************/
package com.hesoyam.pharmacy.employee_management.model;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DateTimeRange dateTimeRange;

    @Enumerated(EnumType.STRING)
    private ShiftType type;

    @ManyToOne(optional=false)
    @JoinColumn(name="pharmacy_id", nullable = false)
    private Pharmacy pharmacy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shift)) return false;
        Shift shift = (Shift) o;
        return Objects.equals(id, shift.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTimeRange getDateTimeRange() {
        return dateTimeRange;
    }

    public void setDateTimeRange(DateTimeRange dateTimeRange) {
        this.dateTimeRange = dateTimeRange;
    }

    public ShiftType getType() {
        return type;
    }

    public void setType(ShiftType type) {
        this.type = type;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public boolean isAvailableFor(DateTimeRange dateTimeRange, Pharmacy pharmacy) {
        if(this.type == ShiftType.WORK){
            return this.dateTimeRange.includes(dateTimeRange) && getPharmacy().equals(pharmacy);
        }
        return false;
    }
}