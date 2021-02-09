package com.hesoyam.pharmacy.medicine.service;

import com.hesoyam.pharmacy.medicine.dto.MedicineSearchDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineSearchResultDTO;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineNotFoundException;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineType;

import java.util.List;

public interface IMedicineService {
    Medicine get(Long id);
    List<Medicine> getAll();
    Medicine create(Medicine medicine);
    List<MedicineType> getAllMedicineTypes();
    List<Medicine> findByMedicineName(String name);
    List<MedicineSearchResultDTO> search(MedicineSearchDTO medicineSearchDTO);
    Medicine findById(Long id) throws MedicineNotFoundException;
    void delete(Long medicineId);

    boolean checkAvailability(String medicineName, int quantity, long pharmacyId);
}
