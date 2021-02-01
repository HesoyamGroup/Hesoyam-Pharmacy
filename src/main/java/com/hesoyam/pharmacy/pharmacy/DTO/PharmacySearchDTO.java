package com.hesoyam.pharmacy.pharmacy.dto;

public class PharmacySearchDTO {

    Long id;
    String name;
    double rating;
    String description;
    String cityName;
    String addressLine;
    Double latitude;
    Double longitude;

    public PharmacySearchDTO() {
    }

    public PharmacySearchDTO(Long id, String name, double rating, String description, String cityName, String addressLine, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.cityName = cityName;
        this.addressLine = addressLine;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
