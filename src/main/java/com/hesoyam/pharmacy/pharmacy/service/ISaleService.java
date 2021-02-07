package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.util.report.ReportResult;
import com.hesoyam.pharmacy.util.report.ReportType;

public interface ISaleService {
    ReportResult getAppointmentSalesReportByAdministrator(User user, ReportType type);
}
