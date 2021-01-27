package com.hesoyam.pharmacy.medicine.service.impl;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineType;
import com.hesoyam.pharmacy.medicine.repository.MedicineRepository;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MedicineService implements IMedicineService {

    @Autowired
    MedicineRepository medicineRepository;


    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine create(Medicine medicine) {
        medicine.setId(null); //Since we don't use DTO, we want to make sure we are not updating a random medicine.
        return medicineRepository.save(medicine);
    }

    @Override
    public List<MedicineType> getAllMedicineTypes() {
        return Arrays.asList(MedicineType.values());
    }


}
