package com.hesoyam.pharmacy.appointment.events;

import com.hesoyam.pharmacy.user.model.Dermatologist;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.util.DateTimeRange;
import org.springframework.context.ApplicationEvent;

public class OnCheckupReservationCompletedEvent extends ApplicationEvent {
    private User user;

    public OnCheckupReservationCompletedEvent(User user){
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
