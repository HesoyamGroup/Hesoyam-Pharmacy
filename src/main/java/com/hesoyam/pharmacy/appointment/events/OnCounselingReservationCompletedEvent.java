package com.hesoyam.pharmacy.appointment.events;

import com.hesoyam.pharmacy.user.model.User;
import org.springframework.context.ApplicationEvent;

public class OnCounselingReservationCompletedEvent extends ApplicationEvent {

    private User user;

    public OnCounselingReservationCompletedEvent(User user){
        super(user);

        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
