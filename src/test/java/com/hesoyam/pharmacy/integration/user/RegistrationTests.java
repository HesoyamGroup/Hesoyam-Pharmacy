package com.hesoyam.pharmacy.integration.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hesoyam.pharmacy.location.model.Address;
import com.hesoyam.pharmacy.location.model.City;
import com.hesoyam.pharmacy.location.service.impl.CityService;
import com.hesoyam.pharmacy.user.dto.RegistrationDTO;
import com.hesoyam.pharmacy.user.model.Gender;
import com.hesoyam.pharmacy.user.service.impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegistrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;


    private static final String UNIQUE_EMAIL = "hesoyampharmacy+unique@gmail.com";
    @Test
    void registrationThenFindPatientByEmailTest() throws Exception {
        mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON_VALUE).content(getUniqueRegistrationRequestJSON())).andExpect(status().is2xxSuccessful());
        Assertions.assertNotNull(userService.findByEmail(UNIQUE_EMAIL));
    }
    private String getUniqueRegistrationRequestJSON() throws JsonProcessingException {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setFirstName("Nikola");
        registrationDTO.setLastName("Dragic");
        registrationDTO.setTelephone("0605553333");
        registrationDTO.setEmail(UNIQUE_EMAIL);
        registrationDTO.setGender(Gender.MALE);
        registrationDTO.setPassword("00000000");
        registrationDTO.setConfirmPassword("00000000");
        City city = cityService.getById(1l);
        Address address = new Address("Koste Sokice 66", Double.valueOf(0), Double.valueOf(0), city);
        registrationDTO.setAddress(address);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(registrationDTO);
    }
}
