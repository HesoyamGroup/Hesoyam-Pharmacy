package com.hesoyam.pharmacy.medicine.service.impl;

import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationStatus;
import com.hesoyam.pharmacy.medicine.repository.MedicineReservationItemRepository;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineReservationItemService implements IMedicineReservationItemService {

    @Autowired
    MedicineReservationItemRepository medicineReservationItemRepository;


    @Override
    public List<MedicineReservationItem> getAll() {
        return medicineReservationItemRepository.findAll();
    }

    @Override
    public MedicineReservationItem create(MedicineReservationItem medicineReservationItem) {
        medicineReservationItem.setId(null);
        return medicineReservationItemRepository.save(medicineReservationItem);
    }

    @Override
    public List<MedicineReservationItem> getAllByPatientId(Long id) {
        return medicineReservationItemRepository.getMedicineReservationItemsByPatientIdAndReservationStatus(id, MedicineReservationStatus.COMPLETED);
    }

    @Override
    public MedicineReservationItem getById(Long id) {
        return medicineReservationItemRepository.getOne(id);
    }
}
