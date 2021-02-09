package com.hesoyam.pharmacy.integration.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hesoyam.pharmacy.user.dto.LoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginUnconfirmedAccountTest() throws Exception{
        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON_VALUE).content(getUnconfirmedAccountLoginDataJSON())).andExpect(status().is(401));
    }

    private String getUnconfirmedAccountLoginDataJSON() throws JsonProcessingException {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("hesoyampharmacy+leksa@gmail.com");
        loginDTO.setPassword("00000000");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(loginDTO);
    }
}


