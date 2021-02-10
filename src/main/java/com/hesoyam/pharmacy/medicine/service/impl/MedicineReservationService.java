package com.hesoyam.pharmacy.medicine.service.impl;

import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationNotFoundException;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationStatus;
import com.hesoyam.pharmacy.medicine.repository.MedicineReservationRepository;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationService;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicineReservationService implements IMedicineReservationService {

    @Autowired
    MedicineReservationRepository medicineReservationRepository;

    @Autowired
    IInventoryItemService inventoryItemService;

    @Override
    public MedicineReservation getById(Long id) throws MedicineReservationNotFoundException {
        return medicineReservationRepository.findById(id).orElseThrow(() -> new MedicineReservationNotFoundException((id)));
    }

    @Override
    public MedicineReservation update(MedicineReservation medicineReservation) throws MedicineReservationNotFoundException {
        MedicineReservation updatedMedicineReservation = medicineReservationRepository.getOne(medicineReservation.getId());
        if(updatedMedicineReservation == null) throw new MedicineReservationNotFoundException(medicineReservation.getId());
        updatedMedicineReservation.update(medicineReservation);
        updatedMedicineReservation = medicineReservationRepository.save(updatedMedicineReservation);

        return updatedMedicineReservation;
    }

    @Override
    public List<MedicineReservation> getAll() {
        return medicineReservationRepository.findAll();
    }

    @Override
    public MedicineReservation create(MedicineReservation medicineReservation) {
        medicineReservation.setId(null);//Since we don't use DTO, we want to make sure we are not updating a random medicine.
        return medicineReservationRepository.save(medicineReservation);
    }

    @Override
    public List<MedicineReservation> getByPatientId(Long id) {
        return medicineReservationRepository.findByPatient_Id(id);
    }

    @Override
    public MedicineReservation getByMedicineReservationCode(String code) {
        return medicineReservationRepository.findByCode(code);
    }

    @Override
    public int getPickedupReservationsCountForPatientId(Long patientId, Long pharmacyId) {
        return medicineReservationRepository.countMedicineReservationsByPatient_IdAndAndMedicineReservationStatusAndPharmacy_Id(patientId, MedicineReservationStatus.COMPLETED, pharmacyId);
    }

    @Override
    public MedicineReservation findByCodeAndPharmacy(String code, long id) {
        return medicineReservationRepository.findByCodeAndPharmacy_Id(code, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public boolean cancelPickup(MedicineReservation toUpdate) throws MedicineReservationNotFoundException {
        if(toUpdate != null) {
            if (!toUpdate.getMedicineReservationStatus().equals(MedicineReservationStatus.COMPLETED)) {
                toUpdate.setMedicineReservationStatus(MedicineReservationStatus.CANCELLED);
                update(toUpdate);
                inventoryItemService.medicineReservationCancelled(toUpdate);
                return true;
            }
        }
        return false;
    }
}
