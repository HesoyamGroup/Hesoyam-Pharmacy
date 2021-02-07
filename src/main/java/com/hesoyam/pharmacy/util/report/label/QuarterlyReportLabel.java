package com.hesoyam.pharmacy.util.report.label;

import java.util.Objects;

public class QuarterlyReportLabel implements ReportLabel<QuarterlyReportLabel>{
    private Integer year;
    private Integer quarter;
    public QuarterlyReportLabel(int year, int quarter) {
        this.year = year;
        this.quarter = quarter;
    }
    public Integer getYear() {
        return year;
    }

    public Integer getQuarter() {
        return quarter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuarterlyReportLabel)) return false;
        QuarterlyReportLabel that = (QuarterlyReportLabel) o;
        return Objects.equals(getYear(), that.getYear()) && Objects.equals(getQuarter(), that.getQuarter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getYear(), getQuarter());
    }

    @Override
    public String toString() {
        return "Q" + quarter + " " + year.toString();
    }

    @Override
    public int compareTo(QuarterlyReportLabel o) {
        int byYear = this.year.compareTo(o.getYear());
        return byYear == 0 ? this.quarter.compareTo(o.getQuarter()) : byYear;
    }
}
