package com.hesoyam.pharmacy.prescription.events;

import com.hesoyam.pharmacy.util.notifications.EmailClient;
import com.hesoyam.pharmacy.util.notifications.EmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class EPrescriptionCompletedListener implements ApplicationListener<OnEPrescriptionCompletedEvent> {

    @Autowired
    private EmailClient emailClient;

    @Override
    public void onApplicationEvent(OnEPrescriptionCompletedEvent onEPrescriptionCompletedEvent) {
        this.handleEvent(onEPrescriptionCompletedEvent);
    }

    private void handleEvent(OnEPrescriptionCompletedEvent ePrescriptionCompletedEvent){
        String subject = "EPrescription completed notification.";
        String recipient = ePrescriptionCompletedEvent.getePrescription().getPatient().getEmail();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dear, \n");
        stringBuilder.append("Your prescription has been completed. \n\n");
        stringBuilder.append("Best regards, \n");
        stringBuilder.append("Hesoyam Pharmacy");
        EmailObject emailObject = new EmailObject(subject,recipient, stringBuilder.toString());
        emailClient.sendEmail(emailObject);
    }
}
