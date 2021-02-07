package com.hesoyam.pharmacy.feedback.service.impl;

import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import com.hesoyam.pharmacy.feedback.dto.ComplaintDataDTO;
import com.hesoyam.pharmacy.feedback.dto.EmployeeComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.dto.PharmacyComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.exceptions.InvalidComplaintRequestException;
import com.hesoyam.pharmacy.feedback.model.Complaint;
import com.hesoyam.pharmacy.feedback.model.ComplaintStatus;
import com.hesoyam.pharmacy.feedback.model.EmployeeComplaint;
import com.hesoyam.pharmacy.feedback.model.PharmacyComplaint;
import com.hesoyam.pharmacy.feedback.repository.ComplaintRepository;
import com.hesoyam.pharmacy.feedback.service.IComplaintService;
import com.hesoyam.pharmacy.feedback.service.IReplyService;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationService;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import com.hesoyam.pharmacy.prescription.service.IEPrescriptionService;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.PharmacistNotFoundException;
import com.hesoyam.pharmacy.user.model.*;
import com.hesoyam.pharmacy.user.service.IDermatologistService;
import com.hesoyam.pharmacy.user.service.IPatientService;
import com.hesoyam.pharmacy.user.service.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintService implements IComplaintService {

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IDermatologistService dermatologistService;

    @Autowired
    private IPharmacistService pharmacistService;

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private IPharmacyService pharmacyService;

    @Autowired
    private IMedicineReservationService medicineReservationService;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private IReplyService replyService;

    @Autowired
    private IEPrescriptionService prescriptionService;

    @Override
    public List<ComplaintDataDTO> getAllUnanswered() {
        List<Complaint> unansweredComplaints = complaintRepository.findAllByComplaintStatus(ComplaintStatus.OPENED);
        return unansweredComplaints.stream().map(complaint -> mapComplaintToDTO(complaint)).collect(Collectors.toList());
    }

    private ComplaintDataDTO mapComplaintToDTO(Complaint complaint){
        Patient patient = complaint.getPatient();
        return new ComplaintDataDTO(complaint.getId(), patient.getFirstName(), patient.getLastName(), patient.getEmail(), complaint.getBody(), complaint.getEntityName(), patient.getGender());
    }

    @Override
    public EmployeeComplaint createEmployeeComplaint(EmployeeComplaintCreateDTO employeeComplaintCreateDTO) throws InvalidComplaintRequestException {
        Patient patient = employeeComplaintCreateDTO.getPatient();
        Employee employee = getEmployeeFromEmployeeComplaintDTO(employeeComplaintCreateDTO);
        validateEmployeeComplaint(patient,employee);
        EmployeeComplaint employeeComplaint = new EmployeeComplaint(employeeComplaintCreateDTO.getBody(), patient, ComplaintStatus.OPENED, employee);
        employeeComplaint = complaintRepository.save(employeeComplaint);

        return employeeComplaint;
    }

    @Override
    public PharmacyComplaint createPharmacyComplaint(PharmacyComplaintCreateDTO pharmacyComplaintCreateDTO) throws InvalidComplaintRequestException {
        Patient patient = pharmacyComplaintCreateDTO.getPatient();
        Pharmacy pharmacy = getPharmacyFromPharmacyComplaintDTO(pharmacyComplaintCreateDTO);
        PharmacyComplaint pharmacyComplaint = new PharmacyComplaint(pharmacyComplaintCreateDTO.getBody(), patient, ComplaintStatus.OPENED, pharmacy);
        validatePharmacyComplaint(pharmacyComplaint);

        pharmacyComplaint =  complaintRepository.save(pharmacyComplaint);

        return pharmacyComplaint;
    }

    @Override
    public Complaint findComplaintByIdAndComplaintStatus(Long id, ComplaintStatus complaintStatus) {
        return complaintRepository.findComplaintByIdAndComplaintStatus(id, complaintStatus);
    }

    @Override
    public Complaint save(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    private void validatePharmacyComplaint(PharmacyComplaint pharmacyComplaint) throws InvalidComplaintRequestException {
        Patient patient = pharmacyComplaint.getPatient();
        Pharmacy pharmacy = pharmacyComplaint.getPharmacy();
        if(pharmacy == null || patient == null) throw new InvalidComplaintRequestException("Both pharmacy and patient must be specified.");
        int numberOfPickedupReservations = medicineReservationService.getPickedupReservationsCountForPatientId(patient.getId(), pharmacy.getId());
        int numberOfCompletedAppointments = appointmentService.getCompletedAppointmentsCountInPharmacyByPatient(pharmacy, patient);
        int numberOfERecipes = prescriptionService.countEPrescriptionsByPatient(patient);

        if(numberOfPickedupReservations == 0 && numberOfCompletedAppointments == 0 && numberOfERecipes == 0)
            throw new InvalidComplaintRequestException("You must have at least one completed medicine reservation or at least one completed appointment.");


    }

    private Pharmacy getPharmacyFromPharmacyComplaintDTO(PharmacyComplaintCreateDTO pharmacyComplaintCreateDTO){
        try{
            return pharmacyService.findOne(pharmacyComplaintCreateDTO.getPharmacyId());
        }catch (EntityNotFoundException entityNotFoundException){
            return null;
        }
    }

    private Employee getEmployeeFromEmployeeComplaintDTO(EmployeeComplaintCreateDTO employeeComplaintCreateDTO){
        //Attempts retrieval of both pharmacist and dermatologists.
        Employee employee;
        Long id = employeeComplaintCreateDTO.getEmployeeId();
        try{
            employee = pharmacistService.getById(id);
        } catch (PharmacistNotFoundException e) {
            employee = null;
        }

        if(employee == null){
            try{
                employee = dermatologistService.getById(id);
            }catch(DermatologistNotFoundException e){
                employee = null;
            }
        }

        return employee;
    }

    private void validateEmployeeComplaint(Patient patient, Employee employee) throws InvalidComplaintRequestException{
        if(patient == null || employee == null) throw new InvalidComplaintRequestException("Both patient and employee must be provided.");
        validateNumberOfRequiredAppointments(patient, employee);
    }

    private void validateNumberOfRequiredAppointments(Patient patient, Employee employee) throws InvalidComplaintRequestException {
        int numberOfCompletedAppointments = getNumberOfAppointments(patient, employee);
        if(numberOfCompletedAppointments == 0) throw new InvalidComplaintRequestException("You must have at least one completed appointment in order to place a complaint.");
    }

    private int getNumberOfAppointments(Patient patient, Employee employee){
        RoleEnum role = employee.getRoleEnum();
        int numberOfCompletedAppointments = 0;

        if(role == RoleEnum.DERMATOLOGIST){
            numberOfCompletedAppointments = appointmentService.getCompletedChecksUpForPatientByDermatologist(patient, (Dermatologist) employee);
        }else{
            numberOfCompletedAppointments = appointmentService.getCompletedCounselingsForPatientByPharmacist(patient, (Pharmacist) employee);
        }

        return numberOfCompletedAppointments;
    }
}
