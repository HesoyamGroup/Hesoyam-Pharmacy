package com.hesoyam.pharmacy.feedback.dto;

import com.hesoyam.pharmacy.appointment.model.CheckUp;

public class DermatologistFeedbackDTO {

    Long dermatologistId;
    String dermatologistFullName;
    Double averageRating;
    Double yourRating;

    public DermatologistFeedbackDTO() {
    }

    public DermatologistFeedbackDTO(CheckUp checkUp){
        this.dermatologistId = checkUp.getDermatologist().getId();
        this.dermatologistFullName = checkUp.getDermatologist().getFirstName()+" "+checkUp.getDermatologist().getLastName();
        this.averageRating = checkUp.getDermatologist().getRating();
        this.yourRating = 0.0;
    }

    public Long getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(Long dermatologistId) {
        this.dermatologistId = dermatologistId;
    }

    public String getDermatologistFullName() {
        return dermatologistFullName;
    }

    public void setDermatologistFullName(String dermatologistFullName) {
        this.dermatologistFullName = dermatologistFullName;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Double getYourRating() {
        return yourRating;
    }

    public void setYourRating(Double yourRating) {
        this.yourRating = yourRating;
    }
}
