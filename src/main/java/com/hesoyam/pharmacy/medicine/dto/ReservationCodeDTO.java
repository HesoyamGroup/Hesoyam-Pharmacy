package com.hesoyam.pharmacy.medicine.dto;

public class ReservationCodeDTO {
    String reservationCode;

    public ReservationCodeDTO(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public ReservationCodeDTO() {
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }
}
