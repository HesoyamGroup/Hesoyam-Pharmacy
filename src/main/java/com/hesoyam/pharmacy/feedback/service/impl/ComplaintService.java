package com.hesoyam.pharmacy.feedback.service.impl;

import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import com.hesoyam.pharmacy.feedback.DTO.EmployeeComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.DTO.PharmacyComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.exceptions.InvalidComplaintRequestException;
import com.hesoyam.pharmacy.feedback.model.ComplaintStatus;
import com.hesoyam.pharmacy.feedback.model.EmployeeComplaint;
import com.hesoyam.pharmacy.feedback.model.PharmacyComplaint;
import com.hesoyam.pharmacy.feedback.repository.ComplaintRepository;
import com.hesoyam.pharmacy.feedback.service.IComplaintService;
import com.hesoyam.pharmacy.user.exceptions.DermatologistNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.PharmacistNotFoundException;
import com.hesoyam.pharmacy.user.model.*;
import com.hesoyam.pharmacy.user.service.IDermatologistService;
import com.hesoyam.pharmacy.user.service.IPatientService;
import com.hesoyam.pharmacy.user.service.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ComplaintRepository complaintRepository;

    @Override
    public EmployeeComplaint createEmployeeComplaint(EmployeeComplaintCreateDTO employeeComplaintCreateDTO) throws InvalidComplaintRequestException {
        Patient patient = getPatientFromEmployeeComplaintDTO(employeeComplaintCreateDTO);
        Employee employee = getEmployeeFromEmployeeComplaintDTO(employeeComplaintCreateDTO);
        validateEmployeeComplaint(patient,employee);
        EmployeeComplaint employeeComplaint = new EmployeeComplaint(employeeComplaintCreateDTO.getBody(), patient, ComplaintStatus.OPENED, employee);
        employeeComplaint = complaintRepository.save(employeeComplaint);

        return employeeComplaint;
    }

    @Override
    public PharmacyComplaint createPharmacyComplaint(PharmacyComplaintCreateDTO pharmacyComplaintCreateDTO) {
        return null;
    }

    private Patient getPatientFromEmployeeComplaintDTO(EmployeeComplaintCreateDTO employeeComplaintCreateDTO){
        Patient patient;
        try{
            patient = patientService.getById(employeeComplaintCreateDTO.getPatientId());
        }catch (PatientNotFoundException patientNotFoundException) {
            patient = null;
        }

        return patient;
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
