package com.hesoyam.pharmacy.user.events;

import com.hesoyam.pharmacy.user.model.User;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompletedEvent extends ApplicationEvent {
    private String appUrl;
    private User user;

    public OnRegistrationCompletedEvent(User patient, String appUrl){
        super(patient);

        this.user = patient;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
