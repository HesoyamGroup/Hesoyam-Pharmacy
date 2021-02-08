package com.hesoyam.pharmacy.util.report.calculator;

import com.hesoyam.pharmacy.pharmacy.model.Sale;
import com.hesoyam.pharmacy.util.report.ReportData;
import com.hesoyam.pharmacy.util.report.ReportResult;
import com.hesoyam.pharmacy.util.report.label.ReportLabel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SalesCalculator<T extends ReportLabel<T>, V extends Sale> {
    private List<ReportData<T>> results = new ArrayList<>();

    public ReportResult getReportResult(List<V> sales){
        for(Sale sale : sales){
            ReportData<T> result = getSaleReport(sale);

            if(results.contains(result)){
                int index = results.indexOf(result);
                results.get(index).addResult(result);
            }else{
                results.add(result);
            }
        }

        Collections.sort(results);

        List<String> labels = results.stream().map(result -> result.getLabel().toString()).collect(Collectors.toList());
        List<Double> data = results.stream().map(ReportData::getData).collect(Collectors.toList());
        return new ReportResult(labels, data);
    }

    protected abstract ReportData<T> getSaleReport(Sale sale);
}
