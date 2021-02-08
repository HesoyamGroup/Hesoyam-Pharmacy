package com.hesoyam.pharmacy.integration.feedback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hesoyam.pharmacy.feedback.dto.EmployeeComplaintCreateDTO;
import com.hesoyam.pharmacy.user.model.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ComplaintTests {


    @Autowired
    private WebApplicationContext context;
    @Autowired
    private  MockMvc mvc;

    @BeforeEach
    public void init(){
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithUserDetails (value = "hesoyampharmacy+veselin@gmail.com")
    void placeInvalidEmployeeComplaintTest() throws Exception {
        //Invalid because he doesn't have any completed appointment with specified employee.
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext (). getAuthentication ();
        User userDetails = (User) authentication.getPrincipal ();
        mvc.perform(post("/complaint/create-employee-complaint").contentType(MediaType.APPLICATION_JSON_VALUE).content(getInvalidEmployeeComplaintRequestJSON()).with(user(userDetails))).andExpect(status().is(400));
    }

    private String getInvalidEmployeeComplaintRequestJSON() throws JsonProcessingException {
        EmployeeComplaintCreateDTO employeeComplaint = new EmployeeComplaintCreateDTO();
        employeeComplaint.setBody("Test complaint body");
        employeeComplaint.setEmployeeId(1l);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(employeeComplaint);
    }
}
