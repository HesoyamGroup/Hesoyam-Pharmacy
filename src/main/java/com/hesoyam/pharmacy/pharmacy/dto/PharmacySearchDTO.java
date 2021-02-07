package com.hesoyam.pharmacy.pharmacy.dto;

import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.pharmacy.dto.PharmacyLocationDTO;

public class PharmacySearchDTO {

    Long id;
    String name;
    double rating;
    String description;
    String cityName;
    String addressLine;
    PharmacyLocationDTO pharmacyLocationDTO;

    public PharmacySearchDTO() {
    }

    public PharmacySearchDTO(Long id, String name, double rating, String description, String cityName, String addressLine, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.cityName = cityName;
        this.addressLine = addressLine;
        this.pharmacyLocationDTO = new PharmacyLocationDTO(latitude, longitude);

    }

    public PharmacySearchDTO(Counseling counseling){
        this.id = counseling.getPharmacy().getId();
        this.name = counseling.getPharmacy().getName();
        this.rating = counseling.getPharmacy().getRating();
        this.description = counseling.getPharmacy().getDescription();
        this.cityName = counseling.getPharmacy().getAddress().getCity().getCityName();
        this.addressLine = counseling.getPharmacy().getAddress().getAddressLine();
        this.pharmacyLocationDTO = new PharmacyLocationDTO(counseling.getPharmacy().getAddress().getLatitude(), counseling.getPharmacy().getAddress().getLongitude());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public PharmacyLocationDTO getPharmacyLocationDTO() {
        return pharmacyLocationDTO;
    }

    public void setPharmacyLocationDTO(PharmacyLocationDTO pharmacyLocationDTO) {
        this.pharmacyLocationDTO = pharmacyLocationDTO;
    }
}
