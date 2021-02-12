package com.hesoyam.pharmacy.medicine.events;

import com.hesoyam.pharmacy.user.model.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MedicineRunningLowListener implements ApplicationListener<MedicineRunningLowEvent> {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(MedicineRunningLowEvent event) {
        this.sendEmail(event);
    }

    private void sendEmail(MedicineRunningLowEvent event) {
        List<Administrator> administrators = event.getAdministrators();
        String medicineName = event.getMedicineName();

        for(Administrator administrator : administrators){
            String recipientEmail = administrator.getEmail();
            String subject = "Running low on medicine!";
            StringBuilder sb = new StringBuilder();
            sb.append("You are currently running low on\n\n");
            sb.append(medicineName);
            sb.append(" . \n\nKing regards,\n Hesoyam Pharmacy");
            String message = sb.toString();

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientEmail);
            email.setSubject(subject);
            email.setText(message);
            mailSender.send(email);
        }
    }
}
