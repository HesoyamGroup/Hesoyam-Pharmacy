package com.hesoyam.pharmacy.feedback.dto;

import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;

public class MedicineFeedbackDTO {

    Long id;
    String medicineName;
    Double averageRating;
    Integer yourRating;
    String comment;

    public MedicineFeedbackDTO() {
    }

    public MedicineFeedbackDTO(MedicineReservationItem medicineReservationItem){
        this.id = medicineReservationItem.getMedicine().getId();
        this.medicineName = medicineReservationItem.getMedicine().getName();
        this.averageRating = medicineReservationItem.getMedicine().getRating();
        this.yourRating = 0;
        this.comment = "";
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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
