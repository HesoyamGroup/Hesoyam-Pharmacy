package com.hesoyam.pharmacy.util.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailClient {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmailObject emailObject){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailObject.getRecipient());
        email.setSubject(emailObject.getSubject());
        email.setText(emailObject.getBody());
        mailSender.send(email);
    }

}
