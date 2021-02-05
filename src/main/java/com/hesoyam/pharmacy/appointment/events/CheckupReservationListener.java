package com.hesoyam.pharmacy.appointment.events;

import com.hesoyam.pharmacy.util.notifications.EmailClient;
import com.hesoyam.pharmacy.util.notifications.EmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CheckupReservationListener implements ApplicationListener<OnCheckupReservationCompletedEvent> {

    @Autowired
    private EmailClient emailClient;

    @Override
    public void onApplicationEvent(OnCheckupReservationCompletedEvent onCheckupReservationCompletedEvent) {
        this.sendCheckupReservationMail(onCheckupReservationCompletedEvent);
    }

    private void sendCheckupReservationMail(OnCheckupReservationCompletedEvent event){

        String recipientEmailAddress = event.getUser().getEmail();
        String subject = "Hesoyam Pharmacy - Checkup Reservation Successful";
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("You have successfully reserved checkup.");
        stringBuilder.append(" . \n\nKind regards,\n Hesoyam Pharmacy");
        String message = stringBuilder.toString();

        emailClient.sendEmail(new EmailObject(subject, recipientEmailAddress, message));
    }
}
