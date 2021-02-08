package com.hesoyam.pharmacy.pharmacy.events;

import com.hesoyam.pharmacy.pharmacy.model.Offer;
import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.pharmacy.model.OrderItem;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.util.notifications.EmailClient;
import com.hesoyam.pharmacy.util.notifications.EmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class OfferAcceptedListener implements ApplicationListener<OnOfferAcceptedEvent> {
    @Autowired
    private EmailClient emailClient;

    private static final int NUM_OF_QUICK_SERVICE_THREADS = 10;
    private final ScheduledExecutorService quickService = Executors.newScheduledThreadPool(NUM_OF_QUICK_SERVICE_THREADS);


    @Override
    public void onApplicationEvent(OnOfferAcceptedEvent onOfferAcceptedEvent) {
        List<Offer> offers = onOfferAcceptedEvent.getOffers();
        if(offers.isEmpty()) return;

        quickService.submit(() -> this.handleEmails(offers));
    }

    private void handleEmails(List<Offer> offers){
        for (Offer offer : offers) {
            if(offer.isAccepted())
                sendAcceptedEmail(offer);
            else
                sendRejectionEmail(offer);
        }
    }

    private void sendAcceptedEmail(Offer offer) {
        Order order = offer.getOrder();
        Pharmacy pharmacy = order.getPharmacy();

        String subject = pharmacy.getName() + " - offer accepted";
        String recipient = offer.getSupplier().getEmail();

        StringBuilder strBuild = getBuiltMessage(order, offer);

        strBuild.append("\n\nwas accepted.");
        strBuild.append("\n\nKind regards,\nHesoyam Group");

        emailClient.sendEmail(new EmailObject(subject, recipient, strBuild.toString()));
    }

    private void sendRejectionEmail(Offer offer){
        Order order = offer.getOrder();
        Pharmacy pharmacy = order.getPharmacy();

        String subject = pharmacy.getName() + " - offer rejected";
        String recipient = offer.getSupplier().getEmail();

        StringBuilder strBuild = getBuiltMessage(order, offer);

        strBuild.append("\n\nwas rejected.");
        strBuild.append("\n\nKind regards,\nHesoyam Group");

        emailClient.sendEmail(new EmailObject(subject, recipient, strBuild.toString()));
    }

    private StringBuilder getBuiltMessage(Order order, Offer offer) {
        List<OrderItem> orderItems = order.getOrderItems();
        StringBuilder strBuild = new StringBuilder();
        strBuild.append(String.format("Order #%d%n%nDeadline: %s%n%n", order.getId(), order.getDeadLine().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
        strBuild.append(String.format("# (Quantity) Medicine%n"));

        for(int i=0; i<orderItems.size(); i++){
            strBuild.append(String.format("%d (%d) %s%n", i+1, orderItems.get(i).getQuantity(), orderItems.get(i).getMedicine().getName()));
        }

        strBuild.append("\nYour offer:\n\n");

        strBuild.append(String.format("Delivery date: %s%nTotal price: %s", offer.getDeliveryDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), offer.getTotalPrice()));
        return strBuild;
    }
}
