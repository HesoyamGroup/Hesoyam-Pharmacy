package com.hesoyam.pharmacy.appointment.DTO;

import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.util.DateTimeRange;

public class CounselingInfoDTO {
    Long counselingId;
    String pharmacistFullName;
    Double rating;
    DateTimeRange range;
    Double price;

    public CounselingInfoDTO() {
    }

    public CounselingInfoDTO(Counseling counseling){
        this.counselingId = counseling.getId();
        this.pharmacistFullName = counseling.getPharmacist().getFirstName()+" "+counseling.getPharmacist().getLastName();
        this.rating = counseling.getPharmacist().getRating();
        this.range = counseling.getDateTimeRange();
        this.price = counseling.getPrice();
    }

    public Long getCounselingId() {
        return counselingId;
    }

    public void setCounselingId(Long counselingId) {
        this.counselingId = counselingId;
    }

    public String getPharmacistFullName() {
        return pharmacistFullName;
    }

    public void setPharmacistFullName(String pharmacistFullName) {
        this.pharmacistFullName = pharmacistFullName;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public DateTimeRange getRange() {
        return range;
    }

    public void setRange(DateTimeRange range) {
        this.range = range;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
