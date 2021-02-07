package com.hesoyam.pharmacy.util.report;

import java.util.List;

public class ReportResult {
    List<String> labels;
    List<Double> data;

    public ReportResult(List<String> labels, List<Double> data) {
        this.labels = labels;
        this.data = data;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<Double> getData() {
        return data;
    }
}
