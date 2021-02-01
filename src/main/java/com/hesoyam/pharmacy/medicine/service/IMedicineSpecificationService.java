package com.hesoyam.pharmacy.medicine.service;

import com.hesoyam.pharmacy.medicine.dto.MedicineSpecificationFindResultDTO;

public interface IMedicineSpecificationService {
    MedicineSpecificationFindResultDTO getById(Long id);
}
