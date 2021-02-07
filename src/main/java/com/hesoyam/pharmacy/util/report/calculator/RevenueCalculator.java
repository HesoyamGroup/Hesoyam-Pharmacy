package com.hesoyam.pharmacy.util.report.calculator;

import com.hesoyam.pharmacy.pharmacy.model.Sale;
import com.hesoyam.pharmacy.util.report.ReportData;
import com.hesoyam.pharmacy.util.report.label.RevenueReportLabel;

import java.time.LocalDate;

public class RevenueCalculator<V extends Sale> extends SalesCalculator<RevenueReportLabel, V> {

    @Override
    protected ReportData<RevenueReportLabel> getSaleReport(Sale sale) {
        LocalDate date = sale.getDateOfSale().toLocalDate();

        RevenueReportLabel label = new RevenueReportLabel(date);
        return new ReportData<>(label, sale.getPrice());
    }
}
