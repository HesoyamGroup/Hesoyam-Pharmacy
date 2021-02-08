package com.hesoyam.pharmacy.appointment.dto;

public class CounselingIDDTO {

    Long id;

    public CounselingIDDTO(Long id) {
        this.id = id;
    }

    public CounselingIDDTO() {
        //Empty ctor for JSON serializer
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
