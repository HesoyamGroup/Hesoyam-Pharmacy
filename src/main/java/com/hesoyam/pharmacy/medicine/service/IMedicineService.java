package com.hesoyam.pharmacy.medicine.service;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineType;

import java.util.List;

public interface IMedicineService {
    List<Medicine> getAll();
    Medicine create(Medicine medicine);
    List<MedicineType> getAllMedicineTypes();
}
