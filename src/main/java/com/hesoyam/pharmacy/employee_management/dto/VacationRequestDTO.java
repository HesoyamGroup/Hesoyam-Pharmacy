package com.hesoyam.pharmacy.employee_management.dto;

import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.employee_management.model.VacationRequestStatus;
import com.hesoyam.pharmacy.user.dto.EmployeeBasicDTO;
import com.hesoyam.pharmacy.util.DateTimeRange;

public class VacationRequestDTO {

    private Long id;
    private DateTimeRange range;
    private VacationRequestStatus status;
    private EmployeeBasicDTO employee;

    private String rejectReason;

    public VacationRequestDTO(){
        //Empty ctor for JSON serializer
    }

    public VacationRequestDTO(VacationRequest vacationRequest){
        this.id = vacationRequest.getId();
        this.range = vacationRequest.getDateTimeRange();
        this.status = vacationRequest.getStatus();
        this.employee = new EmployeeBasicDTO(vacationRequest.getEmployee());
    }

    public EmployeeBasicDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeBasicDTO employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTimeRange getRange() {
        return range;
    }

    public void setRange(DateTimeRange range) {
        this.range = range;
    }

    public VacationRequestStatus getStatus() {
        return status;
    }

    public void setStatus(VacationRequestStatus status) {
        this.status = status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
