package com.hesoyam.pharmacy.unit.prescription;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.prescription.model.EPrescription;
import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EPrescriptionTests {

    @Test
    public void testGetMedicineIdsTest(){
        EPrescription ePrescription = new EPrescription();
        ePrescription.setPrescriptionItems(getMockedPrescriptionItems());
        Assert.assertArrayEquals(ePrescription.getMedicineIds().toArray(), (getMockedMedicines().stream().map(med -> med.getId()).collect(Collectors.toList())).toArray());
    }

    private List<PrescriptionItem> getMockedPrescriptionItems(){
        List<PrescriptionItem> prescriptionItems = new ArrayList<>();
        for(Medicine medicine : getMockedMedicines()){
            PrescriptionItem prescriptionItem = new PrescriptionItem();
            prescriptionItem.setMedicine(medicine);
            prescriptionItems.add(prescriptionItem);
        }
        return prescriptionItems;
    }

    private List<Medicine> getMockedMedicines(){
        List<Medicine> medicineList = new ArrayList<>();
        Medicine med1 = new Medicine();
        med1.setId(1l);
        Medicine med2 = new Medicine();
        med1.setId(2l);
        Medicine med3 = new Medicine();
        med3.setId(3l);
        Medicine med4 = new Medicine();
        med3.setId(4l);
        medicineList.add(med1);
        medicineList.add(med2);
        medicineList.add(med3);
        medicineList.add(med4);
        return medicineList;
    }

}
