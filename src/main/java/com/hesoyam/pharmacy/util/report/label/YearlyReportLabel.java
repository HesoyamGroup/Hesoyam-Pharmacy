package com.hesoyam.pharmacy.util.report.label;

import java.util.Objects;

public class YearlyReportLabel implements ReportLabel<YearlyReportLabel>{

    private Integer year;
    public YearlyReportLabel(int year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YearlyReportLabel)) return false;
        YearlyReportLabel that = (YearlyReportLabel) o;
        return Objects.equals(getYear(), that.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getYear());
    }

    @Override
    public String toString() {
        return year.toString();
    }

    @Override
    public int compareTo(YearlyReportLabel o) {
        return year.compareTo(o.getYear());
    }
}
