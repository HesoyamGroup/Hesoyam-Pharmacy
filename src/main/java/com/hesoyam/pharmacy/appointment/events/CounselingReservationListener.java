package com.hesoyam.pharmacy.appointment.events;

import com.hesoyam.pharmacy.util.notifications.EmailClient;
import com.hesoyam.pharmacy.util.notifications.EmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CounselingReservationListener implements ApplicationListener<OnCounselingReservationCompletedEvent> {

    @Autowired
    private EmailClient emailClient;

    @Override
    public void onApplicationEvent(OnCounselingReservationCompletedEvent onCounselingReservationCompletedEvent) {
        this.sendCounselingReservationMail(onCounselingReservationCompletedEvent);
    }

    private void sendCounselingReservationMail(OnCounselingReservationCompletedEvent event){
        String recipientEmailAddress = event.getUser().getEmail();
        String subject = "Hesoyam Pharmacy - Counseling Reservation Successful";
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("You have successfully reserved counseling.");
        stringBuilder.append(" . \n\nKind regards,\n Hesoyam Pharmacy");
        String message = stringBuilder.toString();

        emailClient.sendEmail(new EmailObject(subject, recipientEmailAddress, message));
    }
}
