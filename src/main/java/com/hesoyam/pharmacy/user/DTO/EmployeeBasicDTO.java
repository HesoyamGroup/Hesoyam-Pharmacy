package com.hesoyam.pharmacy.user.DTO;

import com.hesoyam.pharmacy.user.model.Employee;

public class EmployeeBasicDTO {
    private Long id;
    private String firstName;
    private String lastName;

    public EmployeeBasicDTO(){}

    public EmployeeBasicDTO(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
