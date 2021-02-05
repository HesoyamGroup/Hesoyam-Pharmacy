package com.hesoyam.pharmacy.util.notifications;

import java.util.ArrayList;
import java.util.List;

public class EmailObject {
    private String subject;
    private List<String> recipients = new ArrayList<>();
    private String body;

    public EmailObject(String subject, String recipient, String body) {
        this.subject = subject;
        this.recipients.add(recipient);
        this.body = body;
    }

    public EmailObject(String subject, List<String> recipients, String body){
        this.subject = subject;
        this.recipients = recipients;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
