package com.hesoyam.pharmacy.util.report.label;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

public class MonthlyReportLabel implements ReportLabel<MonthlyReportLabel>{
    private Integer year;
    private Month month;

    public MonthlyReportLabel(int year, Month month) {
        this.year = year;
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public Month getMonth() {
        return month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonthlyReportLabel)) return false;
        MonthlyReportLabel that = (MonthlyReportLabel) o;
        return Objects.equals(getYear(), that.getYear()) && getMonth() == that.getMonth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getYear(), getMonth());
    }

    @Override
    public String toString() {
        return month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + ' ' + year.toString();
    }

    @Override
    public int compareTo(MonthlyReportLabel o) {
        int byYear = this.year.compareTo(o.getYear());
        return byYear == 0 ? this.month.compareTo(o.getMonth()) : byYear;
    }
}
