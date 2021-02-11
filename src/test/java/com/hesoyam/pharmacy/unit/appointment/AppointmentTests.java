package com.hesoyam.pharmacy.unit.appointment;

import com.hesoyam.pharmacy.appointment.model.Counseling;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.user.model.Pharmacist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AppointmentTests {

    @Test
    void testCounselingAndPharmacistWorkplaceCompatibilityIncorrect(){
        Pharmacist pharmacist = new Pharmacist();

        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(1l);

        Pharmacy pharmacy2 = new Pharmacy();
        pharmacy2.setId(2l);

        Counseling counseling = new Counseling();
        counseling.setPharmacy(pharmacy2);

        pharmacist.setPharmacy(pharmacy);
        counseling.setPharmacist(pharmacist);

        Assertions.assertEquals(null, counseling.getPharmacist());
    }

    @Test
    void testCounselingAndPharmacistWorkplaceCompatibilityCorrect(){
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setId(1l);

        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(1l);

        Counseling counseling = new Counseling();
        counseling.setPharmacy(pharmacy);

        pharmacist.setPharmacy(pharmacy);
        counseling.setPharmacist(pharmacist);

        Assertions.assertEquals(1l, counseling.getPharmacist().getId());
    }


}
