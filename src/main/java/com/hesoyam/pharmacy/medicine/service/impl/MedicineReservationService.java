package com.hesoyam.pharmacy.medicine.service.impl;

import com.hesoyam.pharmacy.medicine.dto.MedicineReservationCancellationDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineReservationDTO;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineNotFoundException;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationExpiredCancellationException;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineReservationNotFoundException;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationStatus;
import com.hesoyam.pharmacy.medicine.repository.MedicineReservationRepository;
import com.hesoyam.pharmacy.medicine.service.IMedicineReservationService;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserPenalizedException;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IPatientService;
import com.hesoyam.pharmacy.util.notifications.EmailClient;
import com.hesoyam.pharmacy.util.notifications.EmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MedicineReservationService implements IMedicineReservationService {

    @Autowired
    MedicineReservationRepository medicineReservationRepository;

    @Autowired
    IInventoryItemService inventoryItemService;

    @Autowired
    IPharmacyService pharmacyService;

    @Autowired
    IPatientService patientService;

    @Autowired
    IMedicineService medicineService;

    @Autowired
    IMedicineReservationService medicineReservationService;

    @Autowired
    EmailClient client;

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
    @Transactional(propagation = Propagation.REQUIRED)
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
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean cancelPickup(MedicineReservation toUpdate) throws MedicineReservationNotFoundException {
        if(toUpdate != null) {
            if (!toUpdate.getMedicineReservationStatus().equals(MedicineReservationStatus.COMPLETED) &&
                    !toUpdate.getMedicineReservationStatus().equals(MedicineReservationStatus.CANCELLED)) {
                toUpdate.setMedicineReservationStatus(MedicineReservationStatus.CANCELLED);
                update(toUpdate);
                try {
                    inventoryItemService.medicineReservationCancelled(toUpdate);
                    return true;
                }catch (IllegalArgumentException e){
                    return false;
                }
            }
        }
        return false;
    }

    public List<MedicineReservation> getByCreatedStatus() {
        return medicineReservationRepository.findByMedicineReservationStatus(MedicineReservationStatus.CREATED);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createMedicineReservation(MedicineReservationDTO medicineReservationDTO, User user) throws PatientNotFoundException, MedicineNotFoundException {
        MedicineReservation medicineReservation = new MedicineReservation();
        Patient patient = patientService.getById(user.getId());
        if(patient.getPenaltyPoints() >= 3){
            throw new UserPenalizedException(patient.getId());
        }

        medicineReservation.setPharmacy(pharmacyService.findOne(medicineReservationDTO.getPharmacyId()));
        medicineReservation.setMedicineReservationStatus(MedicineReservationStatus.CREATED);
        medicineReservation.setCode(generateString());
        medicineReservation.setPickUpDate(medicineReservationDTO.getPickUpDate());
        medicineReservation.setPatient(patientService.getById(user.getId()));
        List<MedicineReservationItem> medicineReservationItemList = new ArrayList<>();
        medicineReservationItemList.add(new MedicineReservationItem(1, medicineService.findById(medicineReservationDTO.getMedicineId())));
        medicineReservation.setMedicineReservationItems(medicineReservationItemList);

        create(medicineReservation);

        InventoryItem inventoryItem = inventoryItemService.getInventoryItemByPharmacyIdAndMedicineId(medicineReservationDTO.getPharmacyId(), medicineReservationDTO.getMedicineId());

        if(inventoryItem.reserve(1))
            inventoryItemService.update(inventoryItem);
        else
            throw new IllegalArgumentException("Reservation item quantity can not be greater than available quantity!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MedicineReservation cancelMedicineReservation(MedicineReservationCancellationDTO medicineReservationCancellationDTO, User user) throws MedicineReservationExpiredCancellationException, MedicineReservationNotFoundException {
        MedicineReservation medicineReservation = getByMedicineReservationCode(medicineReservationCancellationDTO.getReservationCode());
        if(medicineReservation.getPickUpDate().isBefore(LocalDateTime.now().plusDays(1)) || !medicineReservation.isCancellable())
        {
            throw new MedicineReservationExpiredCancellationException(medicineReservation.getId());
        }

        medicineReservation.setMedicineReservationStatus(MedicineReservationStatus.CANCELLED);
        update(medicineReservation);

        InventoryItem inventoryItem = inventoryItemService.getInventoryItemByPharmacyIdAndMedicineId(medicineReservationCancellationDTO.getPharmacyId(), medicineReservationCancellationDTO.getMedicineId());

        inventoryItem.setAvailable(inventoryItem.getAvailable()+1);
        inventoryItem.setReserved(inventoryItem.getReserved()-1);
        inventoryItemService.update(inventoryItem);
        return medicineReservation;
    }

    private String generateString() {
        String SOURCES ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();
        int length = 50;
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
        }
        return new String(text);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean confirmPickup(String extractCode) throws MedicineReservationNotFoundException {
        String emailBody = "This email is confirmation that you have successfully picked up order #";
        MedicineReservation toUpdate =  getByMedicineReservationCode(extractCode);
        if(!toUpdate.getMedicineReservationStatus().equals(MedicineReservationStatus.COMPLETED) &&
                !(toUpdate.getPickUpDate().isBefore(LocalDateTime.now()) && toUpdate.getPickUpDate().isAfter(
                        LocalDateTime.now().minus(24, ChronoUnit.HOURS)))) {

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            toUpdate.setMedicineReservationStatus(MedicineReservationStatus.COMPLETED);
            update(toUpdate);

            EmailObject email = new EmailObject("Medicine pickup confirmation!",
                    toUpdate.getPatient().getEmail(), emailBody + toUpdate.getCode() + "!");
//                        "krickovicluka@gmail.com", emailBody + toUpdate.getCode() + "!");
            client.sendEmail(email);
            return true;

        }
        return false;
    }


}
