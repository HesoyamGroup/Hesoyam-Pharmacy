package com.hesoyam.pharmacy.medicine.events;

import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.context.ApplicationEvent;

public class OnMedicineReservationCompletedEvent extends ApplicationEvent {

    private User user;
    private MedicineReservation medicineReservation;

    public OnMedicineReservationCompletedEvent(User patient, MedicineReservation medicineReservation) {
        super(patient);

        this.medicineReservation = medicineReservation;
        this.user = patient;
    }

    public MedicineReservation getMedicineReservation() {
        return medicineReservation;
    }

    public void setMedicineReservationItem(MedicineReservation medicineReservation) {
        this.medicineReservation = medicineReservation;
    }

    public User getUser(){return user;}

    public void setUser(User user) {this.user = user;}
}
