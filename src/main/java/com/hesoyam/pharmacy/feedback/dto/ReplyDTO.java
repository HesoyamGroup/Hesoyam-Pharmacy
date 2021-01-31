package com.hesoyam.pharmacy.feedback.dto;

import com.hesoyam.pharmacy.user.model.SysAdmin;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ReplyDTO {
    @NotNull(message = "Complaint ID must be specified.")
    private Long complaintId;

    @NotNull(message = "Reply text must be specified.")
    @Length(min=5, max=400, message = "Reply length should be between 5 and 400 characters.")
    private String text;

    private SysAdmin sysAdmin;

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SysAdmin getSysAdmin() {
        return sysAdmin;
    }

    public void setSysAdmin(SysAdmin sysAdmin) {
        this.sysAdmin = sysAdmin;
    }
}
