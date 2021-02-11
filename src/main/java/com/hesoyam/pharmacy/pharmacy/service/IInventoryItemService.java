package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.pharmacy.dto.InventoryItemPriceDTO;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItemPrice;
import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;
import com.hesoyam.pharmacy.user.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;


public interface IInventoryItemService {
    List<InventoryItemPrice> create(Long inventoryItemId, InventoryItemPriceDTO itemPriceDTO, User loggedInUser) throws IllegalAccessException;

    List<InventoryItemPrice> update(Long inventoryItemId, InventoryItemPriceDTO itemPriceDTO, User user) throws IllegalAccessException;

    List<InventoryItem> getAllByPharmacy(Long pharmacyId);

    public InventoryItem update(InventoryItem inventoryItem) throws EntityNotFoundException;
    InventoryItem getInventoryItemByPharmacyIdAndMedicineId(Long pharmacyId, Long medicineId) throws EntityNotFoundException;

    void removeItems(List<PrescriptionItem> prescriptionItems, long pharmacyId);

    void cancelReservation(MedicineReservation medicineReservation);

    InventoryItem findByMedicineIdAndInventoryId(Long medicineId, Long pharmacyId);
    void delete(User user, Long id) throws IllegalAccessException;
}
