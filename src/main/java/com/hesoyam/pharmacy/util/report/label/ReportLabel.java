package com.hesoyam.pharmacy.util.report.label;


public interface ReportLabel<T> extends Comparable<T>{

    boolean equals(Object o);
    int hashCode();

    String toString();
}
