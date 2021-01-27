package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.repository.AppointmentRepository;
import com.hesoyam.pharmacy.appointment.repository.TherapyRepository;
import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements IAppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    TherapyRepository therapyRepository;
}
