/***********************************************************************
 * Module:  VacationRequest.java
 * Author:  vule
 * Purpose: Defines the Class VacationRequest
 ***********************************************************************/
package com.hesoyam.pharmacy.employee_management.model;

import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Employee;
import com.hesoyam.pharmacy.user.model.RoleEnum;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.util.DateTimeRange;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VacationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VacationRequestStatus status;

    @Embedded
    private DateTimeRange dateTimeRange;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="employee_id", nullable = false)
    private Employee employee;

    @Column(length = 300)
    @Length(max = 300, message = "Reject reason can be up to 300 characters long.")
    private String rejectReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VacationRequestStatus getStatus() {
        return status;
    }

    public void setStatus(VacationRequestStatus status) {
        this.status = status;
    }

    public DateTimeRange getDateTimeRange() {
        return dateTimeRange;
    }

    public void setDateTimeRange(DateTimeRange dateTimeRange) {
        this.dateTimeRange = dateTimeRange;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public boolean isCreated(){
        return this.status == VacationRequestStatus.CREATED;
    }

    public boolean isRequestedForPharmacy(Pharmacy pharmacy){
        return employee.isWorkingAt(pharmacy);
    }

    public boolean isRecent(){
        return this.dateTimeRange.getTo().isAfter(LocalDateTime.now());
    }

    public void reject(String rejectReason) {
        if(status == VacationRequestStatus.CREATED){
            setStatus(VacationRequestStatus.REJECTED);
            setRejectReason(rejectReason);
        }
        else
            throw new IllegalStateException(String.format("Cannot reject vacation request with status '%s'", status));
    }

    public boolean isPharmacistVacationRequest() {
        return employee.getRoleEnum() == RoleEnum.PHARMACIST;
    }

    public boolean isDermatologistVacationRequest() {
        return employee.getRoleEnum() == RoleEnum.DERMATOLOGIST;
    }
}