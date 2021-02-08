package com.hesoyam.pharmacy.pharmacy.events;

import com.hesoyam.pharmacy.pharmacy.model.Offer;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class OnOfferAcceptedEvent extends ApplicationEvent {

    private final List<Offer> offers;

    public OnOfferAcceptedEvent(List<Offer> offers) {
        super(offers);
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
