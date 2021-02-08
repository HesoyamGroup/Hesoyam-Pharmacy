package com.hesoyam.pharmacy.util.report;

import com.hesoyam.pharmacy.util.report.label.ReportLabel;

import java.util.Objects;

public class ReportData<T extends ReportLabel<T>> implements Comparable<ReportData<T>>{
    private T label;
    private Double data;

    public ReportData(T label, Double data){
        this.label = label;
        this.data = data;
    }

    public ReportData(T label, Integer data){
        this.label = label;
        this.data = Double.valueOf(data);
    }

    public T getLabel() {
        return label;
    }

    public Double getData() {
        return data;
    }

    public void addResult(ReportData<T> result){
        this.data += result.getData();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportData)) return false;
        ReportData<?> that = (ReportData<?>) o;
        return getLabel().equals(that.getLabel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabel());
    }

    @Override
    public int compareTo(ReportData<T> o) {
        return this.label.compareTo(o.getLabel());
    }
}
