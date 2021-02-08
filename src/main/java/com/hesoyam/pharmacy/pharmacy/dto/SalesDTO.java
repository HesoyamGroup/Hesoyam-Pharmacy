package com.hesoyam.pharmacy.pharmacy.dto;

import com.hesoyam.pharmacy.util.report.ReportResult;
import java.util.List;

public class SalesDTO {
    private List<String> labels;
    private List<Double> results;

    public SalesDTO(ReportResult reportResult){
        labels = reportResult.getLabels();
        results = reportResult.getData();
    }

    public SalesDTO(){
        //Empty ctor for JSON serializer
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Double> getResults() {
        return results;
    }

    public void setResults(List<Double> results) {
        this.results = results;
    }
}
