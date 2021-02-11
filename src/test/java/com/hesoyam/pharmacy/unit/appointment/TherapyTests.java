package com.hesoyam.pharmacy.unit.appointment;

import com.hesoyam.pharmacy.appointment.model.TherapyItem;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.medicine.model.MedicineReservation;
import com.hesoyam.pharmacy.medicine.model.MedicineReservationItem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TherapyTests {

    @Test
    void testTherapyItemDayCountIncorrect(){
        TherapyItem therapyItem = new TherapyItem();
        therapyItem.setNumberOfDays(-1);
        Assert.assertEquals(0, therapyItem.getNumberOfDays());
    }

    @Test
    void testTherapyItemDayCountCorrect(){
        TherapyItem therapyItem = new TherapyItem();
        therapyItem.setNumberOfDays(7);
        Assert.assertEquals(7, therapyItem.getNumberOfDays());
    }

    @Test
    void testTherapyItemQuantityIncorrect(){
        TherapyItem therapyItem = new TherapyItem();
        therapyItem.setNumberOfDays(-1);
        Assert.assertEquals(0, therapyItem.getNumberOfDays());
    }

    @Test
    void testTherapyItemQuantityCorrect(){
        TherapyItem therapyItem = new TherapyItem();
        therapyItem.setNumberOfDays(7);
        Assert.assertEquals(7, therapyItem.getNumberOfDays());
    }

    @Test
    void testTherapyItemsFromMedicineReservationItems(){
        MedicineReservation medicineReservation = mockMedicineReservation();

        List<TherapyItem> therapyItems = new ArrayList<>();
        for(MedicineReservationItem item : medicineReservation.getMedicineReservationItems()){
            TherapyItem therapyItem = new TherapyItem();
            therapyItem.setFromMedicineReservation(item);
            therapyItems.add(therapyItem);
        }

        Assert.assertArrayEquals(therapyItems.stream().map(item -> item.getMedicine().getId()).collect(Collectors.toList()).toArray(),
        medicineReservation.getMedicineReservationItems().stream().map(item-> item.getMedicine().getId()).collect(Collectors.toList()).toArray());
    }

    private MedicineReservation mockMedicineReservation() {
        MedicineReservation medicineReservation = new MedicineReservation();
        MedicineReservationItem mrItem = new MedicineReservationItem();
        MedicineReservationItem mrItem2 = new MedicineReservationItem();

        Medicine medicine1 = new Medicine();
        medicine1.setId(1l);
        Medicine medicine2 = new Medicine();
        medicine1.setId(2l);

        mrItem.setId(1l);
        mrItem.setQuantity(1);
        mrItem.setMedicine(medicine1);
        mrItem.setId(2l);
        mrItem2.setQuantity(2);
        mrItem2.setMedicine(medicine2);

        medicineReservation.setId(1l);

        List<MedicineReservationItem> items = new ArrayList<>();
        items.add(mrItem);
        items.add(mrItem2);
        medicineReservation.setMedicineReservationItems(items);

        return medicineReservation;
    }

}
