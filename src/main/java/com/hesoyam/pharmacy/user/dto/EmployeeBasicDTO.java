package com.hesoyam.pharmacy.user.dto;

import com.hesoyam.pharmacy.user.model.Employee;
import com.hesoyam.pharmacy.user.model.RoleEnum;

public class EmployeeBasicDTO extends UserBasicInfoDTO {
    protected RoleEnum role;
    protected double rating;

    public EmployeeBasicDTO(){
        super();
    }

    public EmployeeBasicDTO(Employee employee) {
        super(employee);
        this.rating = employee.getRating();
        this.role = employee.getRoleEnum();
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
