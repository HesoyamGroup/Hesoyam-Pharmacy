package com.hesoyam.pharmacy.integration.feedback;

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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ComplaintTests {


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
    @WithUserDetails (value = "hesoyampharmacy+veselin@gmail.com")
    void placeInvalidEmployeeComplaintTest() throws Exception {
        //Invalid because he doesn't have any completed appointment with specified employee.
        mvc.perform(post("/complaint/create-employee-complaint").contentType(MediaType.APPLICATION_JSON_VALUE).content(getInvalidEmployeeComplaintRequestJSON()).with(user(getUserDetails()))).andExpect(status().is(400));
    }

    @Test
    @WithUserDetails (value = "hesoyampharmacy+veselin@gmail.com")
    void placeInvalidPharmacyComplaintTest() throws Exception{
        //Invalid, no reserved medicine/recipe and other constraints
        mvc.perform(post("/complaint/create-pharmacy-complaint").contentType(MediaType.APPLICATION_JSON_VALUE).content(getInvalidPharmacyComplaintRequestJSON()).with(user(getUserDetails()))).andExpect(status().is(400));
    }
    
    private User getUserDetails(){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext (). getAuthentication ();
        return (User) authentication.getPrincipal ();
    }

    private String getInvalidPharmacyComplaintRequestJSON() throws JsonProcessingException{
        PharmacyComplaintCreateDTO pharmacyComplaintCreateDTO = new PharmacyComplaintCreateDTO();
        pharmacyComplaintCreateDTO.setBody("Test pharmacy complaint body");
        pharmacyComplaintCreateDTO.setPharmacyId(1l);
        return objectWriter.writeValueAsString(pharmacyComplaintCreateDTO);

    }
    private String getInvalidEmployeeComplaintRequestJSON() throws JsonProcessingException {
        EmployeeComplaintCreateDTO employeeComplaint = new EmployeeComplaintCreateDTO();
        employeeComplaint.setBody("Test complaint body");
        employeeComplaint.setEmployeeId(1l);
        return objectWriter.writeValueAsString(employeeComplaint);
    }
}
