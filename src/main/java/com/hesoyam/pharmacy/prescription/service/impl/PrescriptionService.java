package com.hesoyam.pharmacy.prescription.service.impl;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import com.hesoyam.pharmacy.prescription.exceptions.PatientIsAllergicException;
import com.hesoyam.pharmacy.prescription.model.EPrescription;
import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;
import com.hesoyam.pharmacy.prescription.model.PrescriptionStatus;
import com.hesoyam.pharmacy.prescription.repository.PrescriptionRepository;
import com.hesoyam.pharmacy.prescription.service.IPrescriptionService;
import com.hesoyam.pharmacy.user.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrescriptionService implements IPrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private IInventoryItemService inventoryItemService;

    @Autowired
    private IMedicineService medicineService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public EPrescription createPrescription(List<PrescriptionItem> prescriptionItems, Patient patient, long pharmacyId) throws PatientIsAllergicException {
        List<Medicine> allergies = patient.getAllergies();

        testForAllergies(prescriptionItems, allergies);

        if(areAvailable(prescriptionItems, pharmacyId)) {

            EPrescription prescription = new EPrescription();
            prescription.setPrescriptionItems(prescriptionItems);
            prescription.setIssuingDate(LocalDateTime.now());
            prescription.setPatient(patient);
            prescription.setPrescriptionStatus(PrescriptionStatus.ACTIVE);

            prescriptionRepository.save(prescription);
            return prescription;
        }

        return null;
    }

    private boolean areAvailable(List<PrescriptionItem> items, long pharmacyId){
        for(PrescriptionItem item : items){
            if(!medicineService.checkAvailability(item.getMedicine().getName(), item.getQuantity(), pharmacyId)){
                return false;
            }
        }
        return true;
    }

    private void testForAllergies(List<PrescriptionItem> prescriptionItems, List<Medicine> allergies) throws PatientIsAllergicException {
        for(PrescriptionItem item : prescriptionItems){
            if(isAllergic(item, allergies)){
                throw new PatientIsAllergicException(item.getMedicine().getName());
            }
        }
    }

    private boolean isAllergic(PrescriptionItem item, List<Medicine> allergies) {
        for(Medicine allergy : allergies){
            if(allergy.getName().equalsIgnoreCase(item.getMedicine().getName())){
                return true;
            }
        }
        return false;
    }
}
