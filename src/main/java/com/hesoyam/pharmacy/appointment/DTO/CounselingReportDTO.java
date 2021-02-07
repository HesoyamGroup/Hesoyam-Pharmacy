package com.hesoyam.pharmacy.appointment.DTO;

import com.hesoyam.pharmacy.prescription.dto.PrescriptionItemDTO;
import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CounselingReportDTO {
    private String report;
    private List<PrescriptionItemDTO> prescriptionItems;
    private String patientEmail;
    private LocalDateTime from;


    public CounselingReportDTO(String report, List<PrescriptionItemDTO> prescriptionItems, String patientEmail, LocalDateTime from) {
        this.report = report;
        this.prescriptionItems = prescriptionItems;
        this.patientEmail = patientEmail;
        this.from = from;
    }

    public CounselingReportDTO() {
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public List<PrescriptionItemDTO> getPrescriptionItems() {
        return prescriptionItems;
    }

    public void setPrescriptionItems(List<PrescriptionItemDTO> prescriptionItems) {
        this.prescriptionItems = prescriptionItems;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }
}
