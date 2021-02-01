package com.hesoyam.pharmacy.feedback.events;

import com.hesoyam.pharmacy.feedback.model.Complaint;
import org.springframework.context.ApplicationEvent;

public class OnComplaintRepliedEvent extends ApplicationEvent {
    private Complaint complaint;


    public OnComplaintRepliedEvent(Complaint complaint) {
        super(complaint);
        this.complaint = complaint;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }
}
