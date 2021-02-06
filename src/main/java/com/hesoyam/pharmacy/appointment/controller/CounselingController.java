package com.hesoyam.pharmacy.appointment.controller;
import com.hesoyam.pharmacy.appointment.DTO.CounselingReportDTO;
import com.hesoyam.pharmacy.appointment.dto.CounselingDTO;
import com.hesoyam.pharmacy.appointment.exceptions.CounselingNotFoundException;
import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.appointment.service.ICounselingService;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.prescription.dto.PrescriptionItemDTO;
import com.hesoyam.pharmacy.prescription.exceptions.PatientIsAllergicException;
import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;
import com.hesoyam.pharmacy.prescription.service.IPrescriptionService;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import com.hesoyam.pharmacy.user.service.IPatientService;
import com.hesoyam.pharmacy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @PostMapping(value = "/finish-counseling")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> finishCounseling(@RequestBody @Valid CounselingReportDTO counselingReportDTO,
                                                   @AuthenticationPrincipal Pharmacist user){
        Patient patient = patientService.getByEmail(counselingReportDTO.getPatientEmail());
        try {
            counselingService.updateCounselingAfterAppointment(patient.getId(), counselingReportDTO.getFrom(),
                    counselingReportDTO.getReport(), user);
            List<PrescriptionItem> converted = convertPrescriptionItems(counselingReportDTO.getPrescriptionItems());
            try {
                prescriptionService.createPrescription(converted, patient);
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
    public ResponseEntity<List<com.hesoyam.pharmacy.appointment.dto.CounselingDTO>> getAllCounselings(
            @PathVariable String patientEmail, @AuthenticationPrincipal Pharmacist user
    ){
        Patient patient = patientService.getByEmail(patientEmail);
        if(patient != null) {
            List<com.hesoyam.pharmacy.appointment.dto.CounselingDTO> dtos = new ArrayList<>();
            List<Counseling> allCounselings = counselingService.getAllCounselingsForPatientAndPharmacist(patient, user);
            allCounselings.forEach(counseling -> dtos.add(new CounselingDTO(counseling)));
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


}
