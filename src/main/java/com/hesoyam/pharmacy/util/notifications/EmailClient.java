package com.hesoyam.pharmacy.util.notifications;

import com.hesoyam.pharmacy.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailClient {
    @Autowired
    private JavaMailSender mailSender;

    public EmailClient(){}



    public void sendEmail(EmailObject emailObject){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailObject.getRecipient());
        email.setSubject(emailObject.getSubject());
        email.setText(emailObject.getBody());
        mailSender.send(email);
    }

}
