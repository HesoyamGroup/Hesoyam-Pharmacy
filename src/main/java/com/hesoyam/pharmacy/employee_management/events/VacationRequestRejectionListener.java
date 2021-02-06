package com.hesoyam.pharmacy.employee_management.events;

import com.hesoyam.pharmacy.employee_management.model.VacationRequest;
import com.hesoyam.pharmacy.util.notifications.EmailClient;
import com.hesoyam.pharmacy.util.notifications.EmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VacationRequestRejectionListener implements ApplicationListener<OnVacationRequestRejectionEvent> {
    @Autowired
    private EmailClient emailClient;

    @Override
    public void onApplicationEvent(OnVacationRequestRejectionEvent onVacationRequestRejectionEvent) {
        VacationRequest vacationRequest = onVacationRequestRejectionEvent.getVacationRequest();
        sendNotificationMail(vacationRequest);
    }

    private void sendNotificationMail(VacationRequest vacationRequest){
        String subject = "Hesoyam Pharmacy - Your vacation request was rejected";
        String message = String.format("Dear %s %s,%nYour vacation request was rejected.%n%nReason:%n'%s'", vacationRequest.getEmployee().getFirstName(), vacationRequest.getEmployee().getLastName(), vacationRequest.getRejectReason());

        emailClient.sendEmail(new EmailObject(subject, vacationRequest.getEmployee().getEmail(), message));
    }
}
