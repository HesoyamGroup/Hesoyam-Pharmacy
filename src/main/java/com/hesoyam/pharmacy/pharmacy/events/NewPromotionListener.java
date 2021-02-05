package com.hesoyam.pharmacy.pharmacy.events;

import com.hesoyam.pharmacy.pharmacy.model.Promotion;
import com.hesoyam.pharmacy.pharmacy.service.impl.SubscribeService;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.util.notifications.EmailClient;
import com.hesoyam.pharmacy.util.notifications.EmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewPromotionListener implements ApplicationListener<OnNewPromotionEvent> {

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private SubscribeService subscribeService;

    @Override
    public void onApplicationEvent(OnNewPromotionEvent onNewPromotionEvent) {
        Promotion promotion = onNewPromotionEvent.getPromotion();
        List<Patient> subscribedPatients = subscribeService.getSubscribedPatientsByPharmacy(promotion.getPharmacy().getId());
        sendNotificationMail(promotion, subscribedPatients);
    }

    private void sendNotificationMail(Promotion promotion, List<Patient> patients){
        if(patients.isEmpty()) return;

        List<String> recipients = patients.stream().map(User::getEmail).collect(Collectors.toList());
        String pharmacyName = promotion.getPharmacy().getName();
        String fromDate = promotion.getDateTimeRange().getFrom().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String toDate = promotion.getDateTimeRange().getTo().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        String subject = String.format("Hesoyam Pharmacy - %s - %s", promotion.getTitle(), pharmacyName);
        String message = String.format("%s%n%nPromotion lasts from %s to %s", promotion.getDescription(), fromDate, toDate);

        emailClient.sendEmail(new EmailObject(subject, recipients, message));
    }
}
