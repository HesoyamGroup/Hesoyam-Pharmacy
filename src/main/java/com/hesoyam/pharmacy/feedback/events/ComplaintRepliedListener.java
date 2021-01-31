package com.hesoyam.pharmacy.feedback.events;

import com.hesoyam.pharmacy.feedback.model.Complaint;
import com.hesoyam.pharmacy.util.notifications.EmailClient;
import com.hesoyam.pharmacy.util.notifications.EmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ComplaintRepliedListener implements ApplicationListener<OnComplaintRepliedEvent> {

    @Autowired
    private EmailClient emailClient;

    @Override
    public void onApplicationEvent(OnComplaintRepliedEvent onComplaintRepliedEvent) {
        this.sendNotificationEmail(onComplaintRepliedEvent);
    }

    private void sendNotificationEmail(OnComplaintRepliedEvent onComplaintRepliedEvent){
        Complaint complaint = onComplaintRepliedEvent.getComplaint();
        String subject = "Complaint resolved notification.";
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("Your complaint has been resolved: \n");
        stringBuilder.append(complaint.getReply().getText());
        stringBuilder.append(".\n\nKing regards,\n Hesoyam Pharmacy");
        String message = stringBuilder.toString();

        emailClient.sendEmail(new EmailObject(subject, complaint.getPatient().getEmail(), message));
    }
}
