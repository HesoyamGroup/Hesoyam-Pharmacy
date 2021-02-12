package com.hesoyam.pharmacy.integration.appointment;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hesoyam.pharmacy.feedback.dto.EmployeeComplaintCreateDTO;
import com.hesoyam.pharmacy.feedback.dto.PharmacyComplaintCreateDTO;
import com.hesoyam.pharmacy.user.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AppointmentTests {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private  MockMvc mvc;

    private static ObjectMapper objectMapper;
    private static ObjectWriter objectWriter;

    @BeforeAll
    public static void initAll(){
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    }

    @BeforeEach
    public void init(){
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithUserDetails(value = "hesoyampharmacy+mila@gmail.com")
    void placeInvalidAppointmentBooking() throws Exception {
        mvc.perform(post("/appointment/book-new-appointment").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getInvalidBookingRequestJSON()).with(user(getUserDetails()))).andExpect(status().is(400));
    }

    @Test
    @WithUserDetails(value = "hesoyampharmacy+mila@gmail.com")
    void succesfulSearchForUsers() throws Exception{
        mvc.perform(get("/appointment/search-for-user/ves").with(user(getUserDetails()))).andExpect(status().is(200));
    }

    @Test
    @WithUserDetails(value = "hesoyampharmacy+mila@gmail.com")
    void searchForAppointmentsInvalidDate() throws Exception{
        mvc.perform(get("//appointments-for-pharmacist/2021-02-18T17:23:29.176!2021-02-17T17:23:29.176")
                .with(user(getUserDetails()))).andExpect(status().is(404));
    }


    private String getInvalidBookingRequestJSON() throws JsonProcessingException {
        com.hesoyam.pharmacy.appointment.dto.AppointmentBookingDTO bookingDTO = new com.hesoyam.pharmacy.appointment.dto.AppointmentBookingDTO();
        bookingDTO.setFrom(LocalDateTime.now());
        bookingDTO.setTo(LocalDateTime.now().minusHours(1));
        bookingDTO.setPrice(0);
        bookingDTO.setPharmacyId(1);
        bookingDTO.setPatientEmail("hesoyampharmacy+veselin@gmail.com");
        return objectWriter.writeValueAsString(bookingDTO);
    }

    private User getUserDetails(){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext (). getAuthentication ();
        return (User) authentication.getPrincipal ();
    }
}
