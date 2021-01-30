package com.hesoyam.pharmacy.unit;

import com.hesoyam.pharmacy.user.model.Pharmacist;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.charset.StandardCharsets;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class PharmacistTests {

    @Mock
    private Pharmacist pharmacistMock;

    @Autowired
    private MockMvc mockMvc;

    private static final String URL_PREFIX = "/pharmacist";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

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

    @Test
    @WithMockUser(value = "hesoyampharmacy+mila@gmail.com", password = "00000000", roles = "PHARMACIST")
    void testPharmacistUnauthorizedRequest() throws Exception {
        mockMvc.perform(get(URL_PREFIX)).andExpect(status().isForbidden());
    }


}
