package com.hesoyam.pharmacy.medicine.mapper;

import com.hesoyam.pharmacy.medicine.dto.MedicineSearchResultDTO;
import com.hesoyam.pharmacy.medicine.model.Medicine;

public class MedicineMapper {

    private MedicineMapper(){}

    public static MedicineSearchResultDTO mapMedicineToMedicineResultDTO(Medicine medicine){
        return new MedicineSearchResultDTO(
                medicine.getId(),
                medicine.getName(),
                medicine.getManufacturer().getName(),
                medicine.getMedicineType(),
                medicine.getPrescriptionMode(),
                medicine.getRating(),
                medicine.getMedicineSpecification().getId(),
                medicine.getNotes()
        );
    }
}
