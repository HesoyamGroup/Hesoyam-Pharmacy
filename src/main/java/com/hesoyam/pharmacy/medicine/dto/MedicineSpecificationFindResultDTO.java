package com.hesoyam.pharmacy.medicine.dto;

import com.hesoyam.pharmacy.medicine.model.Dosage;

import java.util.List;

public class MedicineSpecificationFindResultDTO {
    private Long id;
    private List<String> contraindications;
    private List<String> compositionItems;
    private Dosage dosage;
    private List<String> replacementMedicines;

    public MedicineSpecificationFindResultDTO() {
    }

    public MedicineSpecificationFindResultDTO(Long id, List<String> contraindications, List<String> compositionItems, Dosage dosage, List<String> replacementMedicines) {
        this.id = id;
        this.contraindications = contraindications;
        this.compositionItems = compositionItems;
        this.dosage = dosage;
        this.replacementMedicines = replacementMedicines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getContraindications() {
        return contraindications;
    }

    public void setContraindications(List<String> contraindications) {
        this.contraindications = contraindications;
    }

    public List<String> getCompositionItems() {
        return compositionItems;
    }

    public void setCompositionItems(List<String> compositionItems) {
        this.compositionItems = compositionItems;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    public List<String> getReplacementMedicines() {
        return replacementMedicines;
    }

    public void setReplacementMedicines(List<String> replacementMedicines) {
        this.replacementMedicines = replacementMedicines;
    }
}
