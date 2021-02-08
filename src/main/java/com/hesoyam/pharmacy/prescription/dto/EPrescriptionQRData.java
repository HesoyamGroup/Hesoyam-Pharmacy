package com.hesoyam.pharmacy.prescription.dto;

import java.time.LocalDateTime;

public class EPrescriptionQRData {
    private Long id;
    private LocalDateTime issuingDate;

    public EPrescriptionQRData(){
        //Empty ctor for JSON serializer
    }

    public EPrescriptionQRData(Long id, LocalDateTime issuingDate) {
        this.id = id;
        this.issuingDate = issuingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(LocalDateTime issuingDate) {
        this.issuingDate = issuingDate;
    }
}
