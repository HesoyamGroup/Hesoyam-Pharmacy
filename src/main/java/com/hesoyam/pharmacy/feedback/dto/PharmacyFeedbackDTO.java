package com.hesoyam.pharmacy.feedback.dto;

import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.feedback.model.PharmacyFeedback;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;

public class PharmacyFeedbackDTO {

    Long pharmacyId;
    String pharmacyName;
    Double averageRating;
    Integer yourRating;
    String comment;

    public PharmacyFeedbackDTO() {
    }

    public PharmacyFeedbackDTO(MedicineReservation medicineReservation){
        this.pharmacyId = medicineReservation.getPharmacy().getId();
        this.pharmacyName = medicineReservation.getPharmacy().getName();
        this.averageRating = medicineReservation.getPharmacy().getRating();
        this.yourRating = 0;
        this.comment = "";
    }

    public PharmacyFeedbackDTO(Counseling counseling){
        this.pharmacyId = counseling.getPharmacy().getId();
        this.pharmacyName = counseling.getPharmacy().getName();
        this.averageRating = counseling.getPharmacy().getRating();
        this.yourRating = 0;
        this.comment = "";
    }

    public PharmacyFeedbackDTO(CheckUp checkup){
        this.pharmacyId = checkup.getPharmacy().getId();
        this.pharmacyName = checkup.getPharmacy().getName();
        this.averageRating = checkup.getPharmacy().getRating();
        this.yourRating = 0;
        this.comment = "";
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
