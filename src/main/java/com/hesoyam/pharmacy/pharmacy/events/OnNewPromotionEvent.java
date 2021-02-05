package com.hesoyam.pharmacy.pharmacy.events;

import com.hesoyam.pharmacy.pharmacy.model.Promotion;
import org.springframework.context.ApplicationEvent;

public class OnNewPromotionEvent extends ApplicationEvent {

    private final Promotion promotion;

    public OnNewPromotionEvent(Promotion promotion) {
        super(promotion);
        this.promotion = promotion;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
