package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.model.Therapy;
import com.hesoyam.pharmacy.appointment.model.TherapyItem;

import java.util.List;

public interface ITherapyService {
    Therapy create(Therapy therapy);
    Therapy createFromItems(List<TherapyItem> items);
}
