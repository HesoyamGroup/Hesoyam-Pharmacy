package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.model.Therapy;
import com.hesoyam.pharmacy.appointment.model.TherapyItem;
import com.hesoyam.pharmacy.appointment.repository.TherapyRepository;
import com.hesoyam.pharmacy.appointment.service.ITherapyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TherapyService implements ITherapyService {
    @Autowired
    private TherapyRepository therapyRepository;

    @Override
    public Therapy create(Therapy therapy) {
        return therapyRepository.save(therapy);
    }

    @Override
    public Therapy createFromItems(List<TherapyItem> items) {
        Therapy therapy = new Therapy();
        therapy.setTherapyItems(items);
        return create(therapy);
    }
}
