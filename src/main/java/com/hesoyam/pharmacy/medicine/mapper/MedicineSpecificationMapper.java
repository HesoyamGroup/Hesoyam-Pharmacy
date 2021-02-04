package com.hesoyam.pharmacy.medicine.mapper;

import com.hesoyam.pharmacy.medicine.dto.MedicineSpecificationFindResultDTO;
import com.hesoyam.pharmacy.medicine.model.CompositionItem;
import com.hesoyam.pharmacy.medicine.model.Contraindication;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineSpecification;

import java.util.List;
import java.util.stream.Collectors;

public class MedicineSpecificationMapper {
    private MedicineSpecificationMapper(){}

    public static MedicineSpecificationFindResultDTO mapMedSpecToFindResultDTO(MedicineSpecification medicineSpecification){
        return new MedicineSpecificationFindResultDTO(
                medicineSpecification.getId(),
                mapContraindicationsToStringList(medicineSpecification.getContraindications()),
                mapCompositionItemsToStringList(medicineSpecification.getCompositionItems()),
                medicineSpecification.getDosage(),
                mapReplacementMedicinesToStringList(medicineSpecification.getReplacementMedicines())
        );
    }
    private static List<String> mapCompositionItemsToStringList(List<CompositionItem> compositionItems){
        return compositionItems.stream().map(compositionItem -> compositionItem.getName()).collect(Collectors.toList());
    }

    private static List<String> mapContraindicationsToStringList(List<Contraindication> contraindications){
        return contraindications.stream().map(contraindication -> contraindication.getName()).collect(Collectors.toList());
    }

    private static List<String> mapReplacementMedicinesToStringList(List<Medicine> replacementMedicines){
        return replacementMedicines.stream().map(medicine -> medicine.getName()).collect(Collectors.toList());
    }
}
