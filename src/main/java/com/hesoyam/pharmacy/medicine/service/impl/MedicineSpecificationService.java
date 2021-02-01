package com.hesoyam.pharmacy.medicine.service.impl;

import com.hesoyam.pharmacy.medicine.dto.MedicineSpecificationFindResultDTO;
import com.hesoyam.pharmacy.medicine.mapper.MedicineSpecificationMapper;
import com.hesoyam.pharmacy.medicine.repository.MedicineSpecificationRepository;
import com.hesoyam.pharmacy.medicine.service.IMedicineSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineSpecificationService implements IMedicineSpecificationService {
    @Autowired
    private MedicineSpecificationRepository medicineSpecificationRepository;

    @Override
    public MedicineSpecificationFindResultDTO getById(Long id) {
        return MedicineSpecificationMapper.mapMedSpecToFindResultDTO(medicineSpecificationRepository.getOne(id));
    }
}
