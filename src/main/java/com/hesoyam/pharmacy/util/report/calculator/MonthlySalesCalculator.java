package com.hesoyam.pharmacy.util.report.calculator;

import com.hesoyam.pharmacy.pharmacy.model.Sale;
import com.hesoyam.pharmacy.util.report.ReportData;
import com.hesoyam.pharmacy.util.report.label.MonthlyReportLabel;

import java.time.LocalDateTime;


public class MonthlySalesCalculator<V extends Sale> extends SalesCalculator<MonthlyReportLabel, V> {

    @Override
    protected ReportData<MonthlyReportLabel> getSaleReport(Sale sale) {
        LocalDateTime date = sale.getDateOfSale();
        MonthlyReportLabel label = new MonthlyReportLabel(date.getYear(), date.getMonth());

        return new ReportData<>(label, 1);
    }
}
