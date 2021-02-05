package com.hesoyam.pharmacy.util.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class EmailClient {
    @Autowired
    private JavaMailSender mailSender;

    private static final int NUM_OF_QUICK_SERVICE_THREADS = 10;

    private final ScheduledExecutorService quickService = Executors.newScheduledThreadPool(NUM_OF_QUICK_SERVICE_THREADS);

    public void sendEmail(EmailObject emailObject){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailObject.getRecipients().toArray(String[]::new));
        email.setSubject(emailObject.getSubject());
        email.setText(emailObject.getBody());

        quickService.submit(() -> mailSender.send(email));
    }

}
