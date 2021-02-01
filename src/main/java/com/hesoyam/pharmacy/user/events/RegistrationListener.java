package com.hesoyam.pharmacy.user.events;

import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.impl.UserService;
import com.hesoyam.pharmacy.util.notifications.EmailClient;
import com.hesoyam.pharmacy.util.notifications.EmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompletedEvent> {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailClient emailClient;

    @Override
    public void onApplicationEvent(OnRegistrationCompletedEvent onRegistrationCompletedEvent) {
        this.sendConfirmationMail(onRegistrationCompletedEvent);
    }

    private void sendConfirmationMail(OnRegistrationCompletedEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientEmailAddress = user.getEmail();
        String subject = "Hesoyam Pharmacy Activation E-Mail";
        String confirmationUrl = event.getAppUrl() + "/auth/confirm-registration?token=" + token;
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("Please confirm your account on ");
        stringBuilder.append(confirmationUrl);
        stringBuilder.append(" . \n\nKing regards,\n Hesoyam Pharmacy");
        String message = stringBuilder.toString();

        emailClient.sendEmail(new EmailObject(subject, recipientEmailAddress, message));
    }
}
