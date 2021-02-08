package com.hesoyam.pharmacy.integration;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AuthorizationTests {
    @Autowired
    private MockMvc mockMvc;

    private static final String URL_PREFIX = "/pharmacist";

    @Test
    @WithMockUser(value = "hesoyampharmacy+mila@gmail.com", password = "00000000", roles = "PHARMACIST")
    void testPharmacistUnauthorizedRequest() throws Exception {
        mockMvc.perform(get(URL_PREFIX)).andExpect(status().isForbidden());
    }
}
