package com.hesoyam.pharmacy.feedback.dto;

import com.hesoyam.pharmacy.user.model.Gender;

public class ComplaintDataDTO {
    private Long id;
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private String complaintBody;
    private String entityName;
    private Gender gender;

    public ComplaintDataDTO(){
        //Empty ctor for JSON serializer
    }

    public ComplaintDataDTO(Long id, String patientFirstName, String patientLastName, String patientEmail, String complaintBody, String entityName, Gender gender) {
        this.id = id;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientEmail = patientEmail;
        this.complaintBody = complaintBody;
        this.entityName = entityName;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getComplaintBody() {
        return complaintBody;
    }

    public void setComplaintBody(String complaintBody) {
        this.complaintBody = complaintBody;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
