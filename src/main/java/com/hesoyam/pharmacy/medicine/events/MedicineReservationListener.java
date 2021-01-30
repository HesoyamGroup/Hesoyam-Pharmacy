package com.hesoyam.pharmacy.medicine.events;

import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MedicineReservationListener implements ApplicationListener<OnMedicineReservationCompletedEvent> {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnMedicineReservationCompletedEvent onMedicineReservationCompletedEvent) {
        this.sendReservationConfimationMail(onMedicineReservationCompletedEvent);
    }

    private void sendReservationConfimationMail(OnMedicineReservationCompletedEvent event){
        User user = event.getUser();
        MedicineReservation medicineReservation = event.getMedicineReservation();

        String recipientEmailAddress = user.getEmail();
        String subject = "Medicine Rerservation Successful";
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("You have successfully reserved medicament!");
        stringBuilder.append("\n\n Unique Code: ");
        stringBuilder.append(medicineReservation.getCode());
        stringBuilder.append(" . \n\nKing regards,\n Hesoyam Pharmacy");
        String message = stringBuilder.toString();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientEmailAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
