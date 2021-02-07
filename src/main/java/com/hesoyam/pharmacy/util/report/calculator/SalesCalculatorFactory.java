package com.hesoyam.pharmacy.util.report.calculator;

import com.hesoyam.pharmacy.pharmacy.model.Sale;
import com.hesoyam.pharmacy.util.report.ReportType;

public class SalesCalculatorFactory<T extends Sale> {

    public SalesCalculator<?, T> getSalesCalculator(ReportType type){
        switch (type){
            case MONTHLY:
                return new MonthlySalesCalculator<>();
            case QUARTERLY:
                return new QuarterlySalesCalculator<>();
            case YEARLY:
                return new YearlySalesCalculator<>();
            case REVENUE:
                return new RevenueCalculator<>();
            default:
                throw new IllegalArgumentException();
        }
    }
}
