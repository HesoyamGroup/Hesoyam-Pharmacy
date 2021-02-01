package com.hesoyam.pharmacy.medicine.service;

import com.hesoyam.pharmacy.medicine.DTO.MedicineSpecificationFindResultDTO;

public interface IMedicineSpecificationService {
    MedicineSpecificationFindResultDTO getById(Long id);
}
