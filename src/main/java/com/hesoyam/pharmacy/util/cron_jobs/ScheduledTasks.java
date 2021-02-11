package com.hesoyam.pharmacy.util.cron_jobs;

import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationNotFoundException;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationStatus;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationService;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.service.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private IMedicineReservationService medicineReservationService;

    @Autowired
    private IPatientService patientService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Transactional
    @Scheduled(cron = "0 0 0 1/1 * ? ")
    public void penalizeNotPickedUpMedicineReservation() throws MedicineReservationNotFoundException {
        log.info("Starting penalizing");
        List<MedicineReservation> medicineReservationList = medicineReservationService.getByCreatedStatus();

        for(MedicineReservation m: medicineReservationList){
            if(m.getPickUpDate().isBefore(LocalDateTime.now())){
                try{
                    m.setMedicineReservationStatus(MedicineReservationStatus.ABSENT);


                    Patient patient = m.getPatient();
                    patient.setPenaltyPoints(patient.getPenaltyPoints()+1);
                    medicineReservationService.update(m);
                } catch (MedicineReservationNotFoundException e) {
                    throw new MedicineReservationNotFoundException(m.getId());}
            }
        }
        log.info("Finished penalizing");
    }

    @Transactional
    @Scheduled(cron = "0 0 0 1 1/1 ?")
    public void resetPenaltyPoints() throws PatientNotFoundException {
        log.info("Starting penalty points reset");
        List<Patient> patients = patientService.getAllWithMoreThanZeroPenaltyPoints();

        for(Patient p: patients){
            p.setPenaltyPoints(0);
            patientService.update(p);
        }
    }
}
