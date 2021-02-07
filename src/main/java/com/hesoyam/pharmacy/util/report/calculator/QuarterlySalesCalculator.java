package com.hesoyam.pharmacy.util.report.calculator;

import com.hesoyam.pharmacy.pharmacy.model.Sale;
import com.hesoyam.pharmacy.util.report.ReportData;
import com.hesoyam.pharmacy.util.report.label.QuarterlyReportLabel;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class QuarterlySalesCalculator<V extends Sale> extends SalesCalculator<QuarterlyReportLabel, V> {
    private static final EnumMap<Month, Integer> QUARTERS = new EnumMap<>(Month.class);
    static {
        QUARTERS.put(Month.JANUARY, 1);
        QUARTERS.put(Month.FEBRUARY, 1);
        QUARTERS.put(Month.MARCH, 1);
        QUARTERS.put(Month.APRIL, 2);
        QUARTERS.put(Month.MAY, 2);
        QUARTERS.put(Month.JUNE, 2);
        QUARTERS.put(Month.JULY, 3);
        QUARTERS.put(Month.AUGUST, 3);
        QUARTERS.put(Month.SEPTEMBER, 3);
        QUARTERS.put(Month.OCTOBER, 4);
        QUARTERS.put(Month.NOVEMBER, 4);
        QUARTERS.put(Month.DECEMBER, 4);
    }

    @Override
    protected ReportData<QuarterlyReportLabel> getSaleReport(Sale sale) {
        LocalDateTime date = sale.getDateOfSale();
        Month month = date.getMonth();

        QuarterlyReportLabel label = new QuarterlyReportLabel(date.getYear(), QUARTERS.get(month));
        return new ReportData<>(label, 1);
    }
}
