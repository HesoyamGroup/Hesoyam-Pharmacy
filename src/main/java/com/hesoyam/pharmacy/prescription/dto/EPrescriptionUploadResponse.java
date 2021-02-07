package com.hesoyam.pharmacy.prescription.dto;

import java.util.List;
import java.util.Map;

public class EPrescriptionUploadResponse {
    Map<String, Integer> requestedMedicine;
    List<PharmacyWithPrescriptionPriceDTO> pharmacies;

    public EPrescriptionUploadResponse(){}

    public EPrescriptionUploadResponse(Map<String, Integer> requestedMedicine, List<PharmacyWithPrescriptionPriceDTO> pharmacies) {
        this.requestedMedicine = requestedMedicine;
        this.pharmacies = pharmacies;
    }

    public Map<String, Integer> getRequestedMedicine() {
        return requestedMedicine;
    }

    public void setRequestedMedicine(Map<String, Integer> requestedMedicine) {
        this.requestedMedicine = requestedMedicine;
    }

    public List<PharmacyWithPrescriptionPriceDTO> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<PharmacyWithPrescriptionPriceDTO> pharmacies) {
        this.pharmacies = pharmacies;
    }
}
