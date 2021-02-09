package com.hesoyam.pharmacy.feedback.dto;

import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.model.Counseling;

public class EmployeeFeedbackDTO {

    Long employeeId;
    String employeeFullName;
    Double averageRating;
    Integer yourRating;
    String yourComment;

    public EmployeeFeedbackDTO() {
    }

    public EmployeeFeedbackDTO(CheckUp checkUp){
        this.employeeId = checkUp.getDermatologist().getId();
        this.employeeFullName = checkUp.getDermatologist().getFirstName()+" "+checkUp.getDermatologist().getLastName();
        this.averageRating = checkUp.getDermatologist().getRating();
        this.yourRating = 0;
    }

    public EmployeeFeedbackDTO(Counseling counseling){
        this.employeeId = counseling.getPharmacist().getId();
        this.employeeFullName = counseling.getPharmacist().getFirstName()+" "+counseling.getPharmacist().getLastName();
        this.averageRating = counseling.getPharmacist().getRating();
        this.yourRating = 0;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getYourComment() {
        return yourComment;
    }

    public void setYourComment(String yourComment) {
        this.yourComment = yourComment;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getYourRating() {
        return yourRating;
    }

    public void setYourRating(Integer yourRating) {
        this.yourRating = yourRating;
    }
}
