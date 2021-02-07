package com.hesoyam.pharmacy.appointment.service.impl;

import com.hesoyam.pharmacy.appointment.model.TherapyItem;
import com.hesoyam.pharmacy.appointment.repository.TherapyItemRepository;
import com.hesoyam.pharmacy.appointment.service.ITherapyItemService;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.prescription.dto.PrescriptionItemDTO;
import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TherapyItemService implements ITherapyItemService {

    @Autowired
    private TherapyItemRepository therapyItemRepository;

    @Autowired
    private IMedicineService medicineService;

    @Override
    public TherapyItem create(TherapyItem therapyItem) {
        therapyItemRepository.save(therapyItem);
        return therapyItem;
    }

    @Override
    public List<TherapyItem> createFromList(List<TherapyItem> therapyItems) {
        therapyItems.forEach(therapyItem -> therapyItemRepository.save(therapyItem));
        return therapyItems;
    }

    @Override
    public List<TherapyItem> createFromPrescriptionItems(List<PrescriptionItemDTO> prescriptionItemDTO){
        List<TherapyItem> created = new ArrayList<>();
        for(PrescriptionItemDTO itemDTO : prescriptionItemDTO){
            List<Medicine> medicineList = medicineService.findByMedicineName(itemDTO.getMedicine());
            if(medicineList != null){
                TherapyItem item = new TherapyItem();
                item.setMedicine(medicineList.get(0));
                item.setQuantity(itemDTO.getQuantity());
                item.setNumberOfDays(itemDTO.getTherapyDuration());
                created.add(item);
            }
        }
        return created;
    }

}
