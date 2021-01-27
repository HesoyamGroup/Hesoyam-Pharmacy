package com.hesoyam.pharmacy.feedback.service;

import com.hesoyam.pharmacy.feedback.DTO.EmployeeComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.DTO.PharmacyComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.exceptions.InvalidComplaintRequestException;
import com.hesoyam.pharmacy.feedback.model.EmployeeComplaint;
import com.hesoyam.pharmacy.feedback.model.PharmacyComplaint;

public interface IComplaintService {
    EmployeeComplaint createEmployeeComplaint(EmployeeComplaintCreateDTO employeeComplaintCreateDTO) throws InvalidComplaintRequestException;
    PharmacyComplaint createPharmacyComplaint(PharmacyComplaintCreateDTO pharmacyComplaintCreateDTO);
}
