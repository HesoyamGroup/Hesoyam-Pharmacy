package com.hesoyam.pharmacy.appointment.controller;

import com.hesoyam.pharmacy.appointment.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private IAppointmentService appointmentService;


}
