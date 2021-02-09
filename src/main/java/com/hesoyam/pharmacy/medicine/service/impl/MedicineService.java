package com.hesoyam.pharmacy.medicine.service.impl;

import com.hesoyam.pharmacy.medicine.dto.MedicineDiscountInfoDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineSearchDTO;
import com.hesoyam.pharmacy.medicine.dto.MedicineSearchResultDTO;
import com.hesoyam.pharmacy.medicine.exceptions.InvalidDeleteMedicineRequestException;
import com.hesoyam.pharmacy.medicine.exceptions.MedicineNotFoundException;
import com.hesoyam.pharmacy.medicine.mapper.MedicineMapper;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineType;
import com.hesoyam.pharmacy.medicine.repository.MedicineRepository;
import com.hesoyam.pharmacy.medicine.service.IMedicineService;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import com.hesoyam.pharmacy.user.model.LoyaltyAccount;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.service.ILoyaltyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineService implements IMedicineService {

    @Autowired
    ILoyaltyAccountService loyaltyAccountService;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    IInventoryItemService inventoryItemService;

    @Override
    public Medicine get(Long id) {
        return medicineRepository.getOne(id);
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine create(Medicine medicine) {
        medicine.setId(null); //Since we don't use DTO, we want to make sure we are not updating a random medicine.
        return medicineRepository.save(medicine);
    }

    @Override
    public List<MedicineType> getAllMedicineTypes() {
        return Arrays.asList(MedicineType.values());
    }

    @Override
    public List<Medicine> findByMedicineName(String name) {
        List<Medicine> medicines = medicineRepository.findAllByNameLike(name);
        if(medicines.isEmpty())
            throw new EntityNotFoundException();

        return medicines;
    }

    @Override
    public List<MedicineSearchResultDTO> search(MedicineSearchDTO medicineSearchDTO) {
        List<Medicine> medicines = medicineRepository.search(medicineSearchDTO, PageRequest.of(medicineSearchDTO.getPage()-1, 10));
        return medicines.stream().map(medicine -> MedicineMapper.mapMedicineToMedicineResultDTO(medicine)).collect(Collectors.toList());
    }

    @Override
    public Medicine findById(Long id) throws MedicineNotFoundException {
        return medicineRepository.findById(id).orElseThrow(() -> new MedicineNotFoundException(id));
    }


    @Override
    public MedicineDiscountInfoDTO getMedicinePriceByPharmacy(Long pharmacyId, Long medicineId, Patient patient) {
        InventoryItem inventoryItem = inventoryItemService.getInventoryItemByPharmacyIdAndMedicineId(pharmacyId, medicineId);
        if(inventoryItem == null) throw new EntityNotFoundException();

        double currentPrice = inventoryItem.getTodayPrice();
        double discountedPrice = currentPrice;
        MedicineDiscountInfoDTO medicineDiscountInfoDTO = new MedicineDiscountInfoDTO(currentPrice, discountedPrice);
        if(patient != null){
            LoyaltyAccount loyaltyAccount = loyaltyAccountService.getPatientLoyaltyAccount(patient);
            medicineDiscountInfoDTO.setHasDiscount(true);
            medicineDiscountInfoDTO.setDiscountedPrice(loyaltyAccount.getDiscountedPrice(currentPrice));
        }
        return medicineDiscountInfoDTO;
    }

    @Override
    public void delete(Long medicineId) {
        try{
            Medicine medicine = medicineRepository.getOne(medicineId);
            medicineRepository.delete(medicine);
        }catch (DataIntegrityViolationException e){
            throw new InvalidDeleteMedicineRequestException("Medicine is used, so you can't delete it.");
        }
    }

    @Override
    public boolean checkAvailability(String medicineName, int quantity, long pharmacyId) {
        Medicine medicine = medicineRepository.findByName(medicineName);
        int stock = inventoryItemService.getInventoryItemByPharmacyIdAndMedicineId(pharmacyId, medicine.getId()).getAvailable();
        if(stock > quantity)
            return true;
        else
            return false;
    }


}
