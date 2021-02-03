package com.hesoyam.pharmacy.appointment.dto;

import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.pharmacy.dto.PharmacyDTO;
import com.hesoyam.pharmacy.user.dto.EmployeeBasicDTO;
import com.hesoyam.pharmacy.util.DateTimeRange;

public class FreeCheckupDTO {
    private Long id;
    private DateTimeRange range;
    private PharmacyDTO pharmacy;
    private double price;
    private EmployeeBasicDTO dermatologist;

    public FreeCheckupDTO(){
        //Empty ctor for JSON serializer
    }

    public FreeCheckupDTO(CheckUp checkUp){
        this.id = checkUp.getId();
        this.range = checkUp.getDateTimeRange();
        this.price = checkUp.getPrice();
        this.pharmacy = new PharmacyDTO(checkUp.getPharmacy());
        this.dermatologist = new EmployeeBasicDTO(checkUp.getDermatologist());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTimeRange getRange() {
        return range;
    }

    public void setRange(DateTimeRange range) {
        this.range = range;
    }

    public PharmacyDTO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public EmployeeBasicDTO getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(EmployeeBasicDTO dermatologist) {
        this.dermatologist = dermatologist;
    }
}
