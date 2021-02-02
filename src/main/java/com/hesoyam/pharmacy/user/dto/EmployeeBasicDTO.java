package com.hesoyam.pharmacy.user.dto;

import com.hesoyam.pharmacy.user.model.Employee;

public class EmployeeBasicDTO {
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected double rating;

    public EmployeeBasicDTO(){}

    public EmployeeBasicDTO(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.rating = employee.getRating();
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
