package com.hesoyam.pharmacy.medicine.controller;

import com.hesoyam.pharmacy.medicine.service.IMedicineReservationItemService;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicine-reservation")
public class MedicineReservationController {

    @Autowired
    private IMedicineReservationService medicineReservationService;

    @Autowired
    private IMedicineReservationItemService medicineReservationItemService;


    
}
