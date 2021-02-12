package com.hesoyam.pharmacy.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.hesoyam.pharmacy.util.DateTimeRange;

import java.time.LocalTime;

public class PharmacistRegistrationDTO extends RegistrationDTO{
    private DateTimeRange shiftRange;
    @JsonFormat(pattern = "HH:mm")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime shiftFrom;
    @JsonFormat(pattern = "HH:mm")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime shiftTo;

    public DateTimeRange getShiftRange() {
        return shiftRange;
    }

    public void setShiftRange(DateTimeRange shiftRange) {
        this.shiftRange = shiftRange;
    }

    public LocalTime getShiftFrom() {
        return shiftFrom;
    }

    public void setShiftFrom(LocalTime shiftFrom) {
        this.shiftFrom = shiftFrom;
    }

    public LocalTime getShiftTo() {
        return shiftTo;
    }

    public void setShiftTo(LocalTime shiftTo) {
        this.shiftTo = shiftTo;
    }
}
