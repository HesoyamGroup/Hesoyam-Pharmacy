package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.model.MedicineSale;
import com.hesoyam.pharmacy.pharmacy.model.ServiceSale;
import com.hesoyam.pharmacy.pharmacy.repository.MedicineSaleRepository;
import com.hesoyam.pharmacy.pharmacy.repository.ServiceSaleRepository;
import com.hesoyam.pharmacy.pharmacy.service.ISaleService;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.repository.AdministratorRepository;
import com.hesoyam.pharmacy.util.report.ReportResult;
import com.hesoyam.pharmacy.util.report.ReportType;
import com.hesoyam.pharmacy.util.report.calculator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService {
    @Autowired
    private ServiceSaleRepository serviceSaleRepository;

    @Autowired
    private MedicineSaleRepository medicineSaleRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public ReportResult getAppointmentSalesReportByAdministrator(User user, ReportType type) {
        Administrator administrator = administratorRepository.getOne(user.getId());

        List<ServiceSale> sales = serviceSaleRepository.getAllByPharmacy_Id(administrator.getPharmacy().getId());

        SalesCalculator<?, ServiceSale> calculator = new SalesCalculatorFactory<ServiceSale>().getSalesCalculator(type);
        return calculator.getReportResult(sales);
    }

    @Override
    public ReportResult getMedicineSalesReportByAdministrator(User user, ReportType type) {
        Administrator administrator = administratorRepository.getOne(user.getId());

        List<MedicineSale> sales = medicineSaleRepository.getAllByPharmacy_Id(administrator.getPharmacy().getId());

        SalesCalculator<?, MedicineSale> calculator = new SalesCalculatorFactory<MedicineSale>().getSalesCalculator(type);
        return calculator.getReportResult(sales);
    }
}
