package com.hesoyam.pharmacy.feedback.service;

import com.hesoyam.pharmacy.feedback.dto.ComplaintDataDTO;
import com.hesoyam.pharmacy.feedback.dto.EmployeeComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.dto.PharmacyComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.dto.ReplyDTO;
import com.hesoyam.pharmacy.feedback.exceptions.InvalidComplaintRequestException;
import com.hesoyam.pharmacy.feedback.exceptions.InvalidReplyRequest;
import com.hesoyam.pharmacy.feedback.model.*;

import java.util.List;

public interface IComplaintService {
    List<ComplaintDataDTO> getAllUnanswered();
    EmployeeComplaint createEmployeeComplaint(EmployeeComplaintCreateDTO employeeComplaintCreateDTO) throws InvalidComplaintRequestException;
    PharmacyComplaint createPharmacyComplaint(PharmacyComplaintCreateDTO pharmacyComplaintCreateDTO) throws InvalidComplaintRequestException;
    Complaint findComplaintByIdAndComplaintStatus(Long id, ComplaintStatus complaintStatus);
    Complaint save(Complaint complaint);
}
