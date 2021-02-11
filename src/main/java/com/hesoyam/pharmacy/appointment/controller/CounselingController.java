package com.hesoyam.pharmacy.appointment.controller;

import com.hesoyam.pharmacy.appointment.dto.*;
import com.hesoyam.pharmacy.appointment.events.OnCounselingReservationCompletedEvent;
import com.hesoyam.pharmacy.appointment.exceptions.CounselingCancellationPeriodExpiredException;
import com.hesoyam.pharmacy.appointment.exceptions.CounselingNotFoundException;
import com.hesoyam.pharmacy.appointment.model.AppointmentStatus;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.model.TherapyItem;
import com.hesoyam.pharmacy.appointment.service.ICounselingService;
import com.hesoyam.pharmacy.appointment.service.ITherapyItemService;
import com.hesoyam.pharmacy.appointment.service.ITherapyService;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.pharmacy.dto.PharmacySearchDTO;
import com.hesoyam.pharmacy.prescription.dto.PrescriptionItemDTO;
import com.hesoyam.pharmacy.prescription.exceptions.PatientIsAllergicException;
import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;
import com.hesoyam.pharmacy.prescription.service.IPrescriptionService;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserPenalizedException;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/counseling", produces = MediaType.APPLICATION_JSON_VALUE)
public class CounselingController {

    @Autowired
    private ICounselingService counselingService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IPrescriptionService prescriptionService;

    @Autowired
    private IMedicineService medicineService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ITherapyItemService therapyItemService;

    @Autowired
    private ITherapyService therapyService;

    @PostMapping(value = "/finish-counseling")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> finishCounseling(@RequestBody @Valid com.hesoyam.pharmacy.appointment.dto.CounselingReportDTO counselingReportDTO,
                                                   @AuthenticationPrincipal Pharmacist user){
        Patient patient = patientService.getByEmail(counselingReportDTO.getPatientEmail());
        try {
            Counseling counseling = counselingService.updateCounselingAfterAppointment(patient.getId(), counselingReportDTO.getFrom(),
                    counselingReportDTO.getReport(), user);
            List<PrescriptionItem> converted = convertPrescriptionItems(counselingReportDTO.getPrescriptionItems());
            try {
                prescriptionService.createPrescription(converted, patient, counseling.getPharmacy().getId());
                List<TherapyItem> therapyItems = therapyItemService.createFromPrescriptionItems(
                        counselingReportDTO.getPrescriptionItems());
                therapyService.createFromItems(therapyItems);
                therapyItemService.createFromList(therapyItems);
            } catch (PatientIsAllergicException e1) {
                e1.printStackTrace();
                return new ResponseEntity<>("Patient is allergic to medicine!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Successfully finished counseling!", HttpStatus.OK);
        } catch (CounselingNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to finish counseling!", HttpStatus.NO_CONTENT);
        }

    }

    private List<PrescriptionItem> convertPrescriptionItems(List<PrescriptionItemDTO> prescriptionItems) {
        List<PrescriptionItem> converted = new ArrayList<>();
        for(PrescriptionItemDTO prescriptionItem : prescriptionItems){
            PrescriptionItem item = new PrescriptionItem();
            item.setMedicine(medicineService.findByMedicineName(prescriptionItem.getMedicine()).get(0));
            item.setQuantity(prescriptionItem.getQuantity());
            converted.add(item);
        }
        return converted;
    }

    @GetMapping(value = "get-all-counselings-for-patient/{patientEmail}")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<CounselingDTO>> getAllCounselings(
            @PathVariable String patientEmail, @AuthenticationPrincipal Pharmacist user
    ){
        Patient patient = patientService.getByEmail(patientEmail);
        if(patient != null) {
            List<CounselingDTO> dtos = new ArrayList<>();
            List<Counseling> allCounselings = counselingService.getAllCounselingsForPatientAndPharmacist(patient, user);
            allCounselings.forEach(counseling -> dtos.add(new CounselingDTO(counseling)));
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping(value = "/free-pharmacies/patient")
    public ResponseEntity<List<PharmacySearchDTO>> getAllPharmaciesWithFreeCounseling(@RequestBody com.hesoyam.pharmacy.appointment.dto.CounselingDateAndTimeDTO counselingDateAndTimeDTO){
        List<Counseling> counselings = counselingService.getAllFreeCounselings();
        List<Counseling> possibleCounselings = new ArrayList<>();

        for(Counseling c: counselings){
            if(c.getDateTimeRange().getFrom().minusMinutes(1).isBefore(counselingDateAndTimeDTO.getDateAndTime()) && c.getDateTimeRange().getTo().plusMinutes(1).isAfter(counselingDateAndTimeDTO.getDateAndTime()))
                possibleCounselings.add(c);
        }

        if(!possibleCounselings.isEmpty()){
            List<PharmacySearchDTO> counselingReservationDTOList = new ArrayList<>();
            for(Counseling c: possibleCounselings){
                if(!checkIfInList(c.getPharmacy().getId(), counselingReservationDTOList)){
                    counselingReservationDTOList.add(new PharmacySearchDTO(c));
                }
            }

            return ResponseEntity.ok().body(counselingReservationDTOList);
        }

        return ResponseEntity.ok().body(new ArrayList<>());
    }


    @PostMapping(value = "/free-pharmacists/patient")
    public ResponseEntity<List<com.hesoyam.pharmacy.appointment.dto.CounselingInfoDTO>> getAvailablePharmacistsByTimeAndPharmacy(@RequestBody com.hesoyam.pharmacy.appointment.dto.CounselingDateAndPharmacyDTO counselingDateAndPharmacyDTO){
        List<Counseling> counselings = counselingService.getFreeCounselingsByPharmacyId(counselingDateAndPharmacyDTO.getId());
        List<Counseling> possibleCounselings = new ArrayList<>();

        for(Counseling c: counselings){
            if(c.getDateTimeRange().getFrom().minusMinutes(1).isBefore(counselingDateAndPharmacyDTO.getDateAndTime()) && c.getDateTimeRange().getTo().plusMinutes(1).isAfter(counselingDateAndPharmacyDTO.getDateAndTime()))
                possibleCounselings.add(c);
        }


        List<com.hesoyam.pharmacy.appointment.dto.CounselingInfoDTO> counselingInfoDTOList = new ArrayList<>();

        possibleCounselings.forEach(counseling -> counselingInfoDTOList.add(new com.hesoyam.pharmacy.appointment.dto.CounselingInfoDTO(counseling)));

        return ResponseEntity.ok().body(counselingInfoDTOList);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping(value = "/reserve")
    public ResponseEntity<CounselingIDDTO> reserveCounseling(@AuthenticationPrincipal User user, @RequestBody CounselingIDDTO counselingIDDTO){

        try{
            counselingService.reserve(counselingIDDTO, user);
            return ResponseEntity.ok().body(counselingIDDTO);
        } catch (CounselingNotFoundException | PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UserPenalizedException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (ObjectOptimisticLockingFailureException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping(value = "/cancel/patient")
    public ResponseEntity<com.hesoyam.pharmacy.appointment.dto.FutureCounselingDTO> cancelFutureCounseling(@RequestBody com.hesoyam.pharmacy.appointment.dto.FutureCounselingDTO futureCounselingDTO){

        try{
            Counseling counseling = counselingService.findById(futureCounselingDTO.getId());

            if(counseling.getDateTimeRange().getFrom().isBefore(LocalDateTime.now().plusDays(1)))
                throw new CounselingCancellationPeriodExpiredException(counseling.getId());

            counseling.setPatient(null);
            counseling.setAppointmentStatus(AppointmentStatus.FREE);

            counseling = counselingService.update(counseling);

            return ResponseEntity.ok().body(new com.hesoyam.pharmacy.appointment.dto.FutureCounselingDTO(counseling));
        } catch (CounselingNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new com.hesoyam.pharmacy.appointment.dto.FutureCounselingDTO());
        } catch (CounselingCancellationPeriodExpiredException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new com.hesoyam.pharmacy.appointment.dto.FutureCounselingDTO());
        }
    }


    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(value = "/future/patient")
    public ResponseEntity<List<com.hesoyam.pharmacy.appointment.dto.FutureCounselingDTO>> getFutureCounselingsByPatient(@AuthenticationPrincipal User user){

        List<Counseling> counselings = counselingService.getUpcomingCounselingsByPatient(user.getId());
        List<com.hesoyam.pharmacy.appointment.dto.FutureCounselingDTO> futureCounselingDTO = new ArrayList<>();
        counselings.forEach(counseling -> futureCounselingDTO.add(new com.hesoyam.pharmacy.appointment.dto.FutureCounselingDTO(counseling)));

        return ResponseEntity.status(HttpStatus.OK).body(futureCounselingDTO);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(value = "/past/patient")
    public ResponseEntity<List<com.hesoyam.pharmacy.appointment.dto.FutureCounselingDTO>> getPastCounselingsByPatient(@AuthenticationPrincipal User user){

        List<Counseling> counselings = counselingService.getAllCompletedCounselingsByPatient(user.getId());
        List<com.hesoyam.pharmacy.appointment.dto.FutureCounselingDTO> pastCounselingDTO = new ArrayList<>();
        counselings.forEach(counseling -> pastCounselingDTO.add(new com.hesoyam.pharmacy.appointment.dto.FutureCounselingDTO(counseling)));

        return ResponseEntity.status(HttpStatus.OK).body(pastCounselingDTO);
    }

    private boolean checkIfInList(Long id, List<PharmacySearchDTO> list){
        for(PharmacySearchDTO c: list){
            if(c.getId() == id)
                return true;
        }
        return false;
    }

}
