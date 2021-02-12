package com.hesoyam.pharmacy.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.hesoyam.pharmacy.util.DateTimeRange;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class DermatologistAddPharmacyDTO {
    @NotNull(message = "Dermatologist id must be specified")
    private Long id;
    private DateTimeRange shiftRange;
    @JsonFormat(pattern = "HH:mm")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime shiftFrom;
    @JsonFormat(pattern = "HH:mm")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime shiftTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
