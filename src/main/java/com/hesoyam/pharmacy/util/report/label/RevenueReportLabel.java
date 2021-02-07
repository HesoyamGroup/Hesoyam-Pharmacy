package com.hesoyam.pharmacy.util.report.label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class RevenueReportLabel implements ReportLabel<RevenueReportLabel> {

    private LocalDate date;

    public RevenueReportLabel(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RevenueReportLabel)) return false;
        RevenueReportLabel that = (RevenueReportLabel) o;
        return Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate());
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public int compareTo(RevenueReportLabel o) {
        return this.date.compareTo(o.getDate());
    }
}
