package com.hesoyam.pharmacy.appointment.service;

import com.hesoyam.pharmacy.appointment.model.TherapyItem;
import com.hesoyam.pharmacy.prescription.dto.PrescriptionItemDTO;

import java.util.List;

public interface ITherapyItemService {
    TherapyItem create(TherapyItem therapyItem);
    List<TherapyItem> createFromList(List<TherapyItem> therapyItems);
    List<TherapyItem> createFromPrescriptionItems(List<PrescriptionItemDTO> prescriptionItemDTO);
}
