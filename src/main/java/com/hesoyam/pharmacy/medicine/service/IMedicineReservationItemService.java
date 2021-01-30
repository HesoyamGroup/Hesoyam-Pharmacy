package com.hesoyam.pharmacy.medicine.service;

import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;

import java.util.List;

public interface IMedicineReservationItemService {
    List<MedicineReservationItem> getAll();
    MedicineReservationItem create(MedicineReservationItem medicineReservationItem);
}
