package com.hesoyam.pharmacy.unit;

import com.hesoyam.pharmacy.user.model.Pharmacist;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
class PharmacistTests {

    @Test
    void testPharmacistNameMatching(){
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setFirstName("Milan");
        pharmacist.setLastName("Jankovic");

        boolean result1 = pharmacist.startsWithName("mil", "Jan");
        boolean result2 = pharmacist.startsWithName("M", "jankov");
        boolean result3 = pharmacist.startsWithName("", "");

        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
        assertThat(result3).isTrue();
    }
}
