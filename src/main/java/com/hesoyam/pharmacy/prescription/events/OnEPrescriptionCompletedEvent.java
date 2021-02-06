package com.hesoyam.pharmacy.prescription.events;

import com.hesoyam.pharmacy.prescription.model.EPrescription;
import org.springframework.context.ApplicationEvent;

public class OnEPrescriptionCompletedEvent extends ApplicationEvent {
    private EPrescription ePrescription;
    public OnEPrescriptionCompletedEvent(EPrescription source) {
        super(source);
    }

    public EPrescription getePrescription() {
        return ePrescription;
    }

    public void setePrescription(EPrescription ePrescription) {
        this.ePrescription = ePrescription;
    }
}
