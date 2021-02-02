package com.hesoyam.pharmacy.util;

import java.util.Date;

public class DateRange {
    private Date from;
    private Date to;

    public DateRange(Date from, Date to) {
        this.from = from;
        this.to = to;
    }
    public DateRange() {}

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
