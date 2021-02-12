package com.hesoyam.pharmacy.feedback.controller;

import com.hesoyam.pharmacy.appointment.model.CheckUp;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.service.ICheckUpService;
import com.hesoyam.pharmacy.appointment.service.ICounselingService;
import com.hesoyam.pharmacy.feedback.dto.EmployeeFeedbackDTO;
import com.hesoyam.pharmacy.feedback.dto.MedicineFeedbackDTO;
import com.hesoyam.pharmacy.feedback.dto.PharmacyFeedbackDTO;
import com.hesoyam.pharmacy.feedback.model.EmployeeFeedback;
import com.hesoyam.pharmacy.feedback.model.MedicineFeedback;
import com.hesoyam.pharmacy.feedback.model.PharmacyFeedback;
import com.hesoyam.pharmacy.feedback.service.IEmployeeFeedbackService;
import com.hesoyam.pharmacy.feedback.service.IMedicineFeedbackService;
import com.hesoyam.pharmacy.feedback.service.IPharmacyFeedbackService;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineNotFoundException;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationItemService;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationService;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.pharmacy.exceptions.PharmacyNotFoundException;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import com.hesoyam.pharmacy.user.exceptions.EmployeeNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IEmployeeService;
import com.hesoyam.pharmacy.user.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/feedback", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeedbackController {

    @Autowired
    private ICheckUpService checkUpService;

    @Autowired
    private ICounselingService counselingService;

    @Autowired
    private IEmployeeFeedbackService employeeFeedbackService;

    @Autowired
    private IMedicineReservationService medicineReservationService;

    @Autowired
    private IMedicineService medicineService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IMedicineReservationItemService medicineReservationItemService;

    @Autowired
    private IMedicineFeedbackService medicineFeedbackService;

    @Autowired
    private IPharmacyFeedbackService pharmacyFeedbackService;

    @Autowired
    private IPharmacyService pharmacyService;

    @Secured("ROLE_PATIENT")
    @GetMapping(value = "/checkups")
    public ResponseEntity<List<EmployeeFeedbackDTO>> getPastCheckups(@AuthenticationPrincipal User user){

        List<CheckUp> pastCheckups = checkUpService.getAllCompletedCheckupsByPatient(user.getId());

        List<EmployeeFeedbackDTO> employeeFeedbackDTOList = new ArrayList<>();
        if(!pastCheckups.isEmpty()){
            for(CheckUp c: pastCheckups){
                if(!isEmployeeInList(c.getDermatologist().getId(), employeeFeedbackDTOList)){
                    employeeFeedbackDTOList.add(new EmployeeFeedbackDTO(c));
                }
            }
        }

        return ResponseEntity.ok().body(employeeFeedbackDTOList);
    }

    @Secured("ROLE_PATIENT")
    @GetMapping(value = "/counselings")
    public ResponseEntity<List<EmployeeFeedbackDTO>> getPastCounselings(@AuthenticationPrincipal User user){

        List<Counseling> pastCounselings = counselingService.getAllCompletedCounselingsByPatient(user.getId());

        List<EmployeeFeedbackDTO> employeeFeedbackDTOList = new ArrayList<>();
        if(!pastCounselings.isEmpty()){
            for(Counseling c: pastCounselings){
                if(!isEmployeeInList(c.getPharmacist().getId(), employeeFeedbackDTOList)){
                    employeeFeedbackDTOList.add(new EmployeeFeedbackDTO(c));
                }
            }
        }

        return ResponseEntity.ok().body(employeeFeedbackDTOList);
    }

    @Secured("ROLE_PATIENT")
    @GetMapping(value = "/medicine")
    public ResponseEntity<List<MedicineFeedbackDTO>> getPastMedicine(@AuthenticationPrincipal User user){
        List<MedicineReservationItem> medicineReservationItems = medicineReservationItemService.getAllByPatientId(user.getId());
        List<MedicineFeedbackDTO> pastMedicineList = new ArrayList<>();

        if(!medicineReservationItems.isEmpty()){
            for(MedicineReservationItem mri: medicineReservationItems){
                if(!isMedicineInList(mri.getMedicine().getId(), pastMedicineList)){
                    pastMedicineList.add(new MedicineFeedbackDTO(mri));
                }
            }
        }

        return ResponseEntity.ok().body(pastMedicineList);
    }

    @Secured("ROLE_PATIENT")
    @GetMapping(value = "/pharmacies")
    public ResponseEntity<List<PharmacyFeedbackDTO>> getInteractedPharmacies(@AuthenticationPrincipal User user){
        List<MedicineReservation> medicineReservation = medicineReservationService.getByPatientId(user.getId());
        List<Counseling> counseling = counselingService.getAllCompletedCounselingsByPatient(user.getId());
        List<CheckUp> checkUps = checkUpService.getAllCompletedCheckupsByPatient(user.getId());

        List<PharmacyFeedbackDTO> pastPharmacies = new ArrayList<>();

        if(!medicineReservation.isEmpty()){
            for(MedicineReservation mri: medicineReservation){
                if(!isInPharmacyList(mri.getPharmacy().getId(), pastPharmacies))
                    pastPharmacies.add(new PharmacyFeedbackDTO(mri));
            }
        }

        if(!counseling.isEmpty()){
            for(Counseling c: counseling){
                if(!isInPharmacyList(c.getPharmacy().getId(), pastPharmacies))
                    pastPharmacies.add(new PharmacyFeedbackDTO(c));
            }
        }

        if(!checkUps.isEmpty()){
            for(CheckUp ch: checkUps){
                if(!isInPharmacyList(ch.getPharmacy().getId(), pastPharmacies))
                    pastPharmacies.add(new PharmacyFeedbackDTO(ch));
            }
        }

        return ResponseEntity.ok().body(pastPharmacies);

    }

    @Secured("ROLE_PATIENT")
    @PostMapping(value = "/employee")
    public ResponseEntity<Double> dermatologistFeedback(@RequestBody EmployeeFeedbackDTO employeeFeedbackDTO, @AuthenticationPrincipal User user) {
        EmployeeFeedback employeeFeedback = employeeFeedbackService.getByEmployeeIdAndPatientId(employeeFeedbackDTO.getEmployeeId(), user.getId());
        if(employeeFeedback != null){
            employeeFeedback.setRating(employeeFeedbackDTO.getYourRating());
            employeeFeedback.setComment(employeeFeedbackDTO.getYourComment());

            employeeFeedbackService.update(employeeFeedback);
        }
        else{
            try {
                employeeFeedback = new EmployeeFeedback();
                employeeFeedback.setComment(employeeFeedbackDTO.getYourComment());
                employeeFeedback.setRating(employeeFeedbackDTO.getYourRating());
                employeeFeedback.setEmployee(employeeService.getOne(employeeFeedbackDTO.getEmployeeId()));
                employeeFeedback.setPatient(patientService.getById(user.getId()));

                employeeFeedbackService.create(employeeFeedback);
            }catch (PatientNotFoundException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-2.0);
            }
        }
        double newRating = employeeFeedbackService.calculateEmployeeRating(employeeFeedbackDTO.getEmployeeId());
        try{
            employeeService.updateRating(employeeFeedbackDTO.getEmployeeId(), newRating);
        }catch (EmployeeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1.0);
        }

        return ResponseEntity.ok().body(newRating);
    }

    @Secured("ROLE_PATIENT")
    @PostMapping(value = "/medicine")
    public ResponseEntity<Double> medicineFeedback(@RequestBody MedicineFeedbackDTO medicineFeedbackDTO, @AuthenticationPrincipal User user) {
        MedicineFeedback medicineFeedback = medicineFeedbackService.findByMedicineIdAndPatientId(medicineFeedbackDTO.getId(), user.getId());

        if(medicineFeedback == null){
            try{
                medicineFeedback = new MedicineFeedback();
                medicineFeedback.setRating(medicineFeedbackDTO.getYourRating());
                medicineFeedback.setComment(medicineFeedbackDTO.getComment());
                medicineFeedback.setPatient(patientService.getById(user.getId()));
                medicineFeedback.setMedicine(medicineService.findById(medicineFeedbackDTO.getId()));

                medicineFeedbackService.create(medicineFeedback);

            } catch (PatientNotFoundException | MedicineNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-2.0);
            }
        }
        else{
            medicineFeedback.setRating(medicineFeedbackDTO.getYourRating());
            medicineFeedback.setComment(medicineFeedbackDTO.getComment());

            medicineFeedbackService.update(medicineFeedback);
        }

        double newRating = medicineFeedbackService.calculateMedicineRating(medicineFeedbackDTO.getId());
        try{
            medicineService.updateRating(medicineFeedbackDTO.getId(), newRating);
        }catch (MedicineNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1.0);
        }

        return ResponseEntity.ok().body(newRating);
    }

    @Secured("ROLE_PATIENT")
    @PostMapping(value = "/pharmacies")
    public ResponseEntity<Double> pharmacyFeedback(@RequestBody PharmacyFeedbackDTO pharmacyFeedbackDTO, @AuthenticationPrincipal User user){
        PharmacyFeedback pharmacyFeedback = pharmacyFeedbackService.findByPharmacyIdAndPatientId(pharmacyFeedbackDTO.getPharmacyId(), user.getId());

        if(pharmacyFeedback == null){
            try{
                pharmacyFeedback = new PharmacyFeedback();
                pharmacyFeedback.setPatient(patientService.getById(user.getId()));
                pharmacyFeedback.setPharmacy(pharmacyService.findOne(pharmacyFeedbackDTO.getPharmacyId()));
                pharmacyFeedback.setComment(pharmacyFeedbackDTO.getComment());
                pharmacyFeedback.setRating(pharmacyFeedbackDTO.getYourRating());

                pharmacyFeedbackService.create(pharmacyFeedback);
            } catch (PatientNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-2.0);
            }
        }
        else{
            pharmacyFeedback.setRating(pharmacyFeedbackDTO.getYourRating());
            pharmacyFeedback.setComment(pharmacyFeedbackDTO.getComment());

            pharmacyFeedbackService.update(pharmacyFeedback);
        }

        double newRating = pharmacyFeedbackService.calculatePharmacyRating(pharmacyFeedbackDTO.getPharmacyId());
        try{
            pharmacyService.updateRating(pharmacyFeedbackDTO.getPharmacyId(), newRating);
        } catch (PharmacyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1.0);
        }

        return ResponseEntity.ok().body(newRating);
    }


    private boolean isMedicineInList(Long id, List<MedicineFeedbackDTO> medicineFeedbackDTOList){
        for(MedicineFeedbackDTO m: medicineFeedbackDTOList) {
            if (m.getId().equals(id))
                return true;
        }
        return false;
    }

    private boolean isEmployeeInList(Long id, List<EmployeeFeedbackDTO> employeeFeedbackDTOList){
        for(EmployeeFeedbackDTO d: employeeFeedbackDTOList){
            if(d.getEmployeeId().equals(id))
                return true;
        }
        return false;
    }

    private boolean isInPharmacyList(Long id, List<PharmacyFeedbackDTO> pharmacyFeedbackDTOList){
        for(PharmacyFeedbackDTO p: pharmacyFeedbackDTOList){
            if(p.getPharmacyId().equals(id))
                return true;
        }
        return false;
    }

}
