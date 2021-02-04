package com.hesoyam.pharmacy.feedback.service;

import com.hesoyam.pharmacy.feedback.dto.ComplaintDataDTO;
import com.hesoyam.pharmacy.feedback.dto.EmployeeComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.dto.PharmacyComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.exceptions.InvalidComplaintRequestException;
import com.hesoyam.pharmacy.feedback.model.Complaint;
import com.hesoyam.pharmacy.feedback.model.ComplaintStatus;
import com.hesoyam.pharmacy.feedback.model.EmployeeComplaint;
import com.hesoyam.pharmacy.feedback.model.PharmacyComplaint;

import java.util.List;

public interface IComplaintService {
    List<ComplaintDataDTO> getAllUnanswered();
    EmployeeComplaint createEmployeeComplaint(EmployeeComplaintCreateDTO employeeComplaintCreateDTO) throws InvalidComplaintRequestException;
    PharmacyComplaint createPharmacyComplaint(PharmacyComplaintCreateDTO pharmacyComplaintCreateDTO) throws InvalidComplaintRequestException;
    Complaint findComplaintByIdAndComplaintStatus(Long id, ComplaintStatus complaintStatus);
    Complaint save(Complaint complaint);
}
