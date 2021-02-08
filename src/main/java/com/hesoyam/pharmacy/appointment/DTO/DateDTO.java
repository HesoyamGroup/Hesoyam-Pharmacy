package com.hesoyam.pharmacy.appointment.dto;

public class DateDTO {
    private String from;
    private String to;

    public DateDTO(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public DateDTO() {
        //Empty ctor for JSON serializer
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
