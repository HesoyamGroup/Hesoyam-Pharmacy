package com.hesoyam.pharmacy.unit.pharmacy;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class InventoryItemTests {

    @Test
    void testInventoryItemReservationCounselation(){
        Medicine medicine = new Medicine();
        medicine.setId(1l);
        medicine.setName("Panklav");

        MedicineReservationItem medicineReservationItem = new MedicineReservationItem();
        medicineReservationItem.setMedicine(medicine);
        medicineReservationItem.setId(1l);
        medicineReservationItem.setQuantity(5);

        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setMedicine(medicine);
        inventoryItem.setAvailable(0);
        inventoryItem.setReserved(5);

        inventoryItem.reservationCancelled(medicineReservationItem);
        Assert.assertEquals(5, inventoryItem.getAvailable());
    }
}
