package com.hesoyam.pharmacy.employee_management.dto;

import com.hesoyam.pharmacy.employee_management.model.Shift;
import com.hesoyam.pharmacy.employee_management.model.ShiftType;
import com.hesoyam.pharmacy.pharmacy.dto.PharmacyDTO;
import com.hesoyam.pharmacy.util.DateTimeRange;

public class ShiftDTO {

    private Long id;
    private DateTimeRange range;
    private ShiftType type;
    private PharmacyDTO pharmacy;

    public ShiftDTO(){
        //Empty ctor for JSON serializer
    }

    public ShiftDTO(Shift shift){
        this.id = shift.getId();
        this.range = shift.getDateTimeRange();
        this.type = shift.getType();
        this.pharmacy = new PharmacyDTO(shift.getPharmacy());
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

    public ShiftType getType() {
        return type;
    }

    public void setType(ShiftType type) {
        this.type = type;
    }

    public PharmacyDTO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }
}
