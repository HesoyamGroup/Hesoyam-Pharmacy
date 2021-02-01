package com.hesoyam.pharmacy.medicine.service.impl;

import com.hesoyam.pharmacy.medicine.DTO.MedicineSearchDTO;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineType;
import com.hesoyam.pharmacy.medicine.repository.MedicineRepository;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public List<Medicine> findByMedicineName(String name) {
        List<Medicine> medicines = medicineRepository.findAllByNameLike(name);
        if(medicines.isEmpty())
            throw new EntityNotFoundException();

        return medicines;
    }

    @Override
    public List<Medicine> search(MedicineSearchDTO medicineSearchDTO) {
        return medicineRepository.search(medicineSearchDTO);
    }


}
