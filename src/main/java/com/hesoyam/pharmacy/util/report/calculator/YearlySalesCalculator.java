package com.hesoyam.pharmacy.util.report.calculator;

import com.hesoyam.pharmacy.pharmacy.model.Sale;
import com.hesoyam.pharmacy.util.report.ReportData;
import com.hesoyam.pharmacy.util.report.label.YearlyReportLabel;

import java.time.LocalDateTime;

public class YearlySalesCalculator<V extends Sale> extends SalesCalculator<YearlyReportLabel, V>{

    @Override
    protected ReportData<YearlyReportLabel> getSaleReport(Sale sale) {
        LocalDateTime date = sale.getDateOfSale();

        YearlyReportLabel label = new YearlyReportLabel(date.getYear());
        return new ReportData<>(label, 1);
    }
}
