package com.hesoyam.pharmacy.integration.salesreport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hesoyam.pharmacy.pharmacy.dto.RevenueDTO;
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

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SalesReportTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void init(){
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithUserDetails(value = "hesoyampharmacy+dragic@gmail.com")
    void testSalesReportMonthlyAppointments() throws Exception {
        mvc.perform(get("/sales/appointment?type=MONTHLY")
                    .with(user(getUserDetails())))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(jsonPath("$..labels[0]").value("Dec 2020"))
                    .andExpect(jsonPath("$..labels[1]").value("Jan 2021"))
                    .andExpect(jsonPath("$..labels[2]").value("Feb 2021"));
    }

    @Test
    @WithUserDetails(value = "hesoyampharmacy+dragic@gmail.com")
    void testSalesReportQuarterlyAppointments() throws Exception {
        mvc.perform(get("/sales/appointment?type=QUARTERLY")
                .with(user(getUserDetails())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$..labels[0]").value("Q4 2020"))
                .andExpect(jsonPath("$..labels[1]").value("Q1 2021"));
    }

    @Test
    @WithUserDetails(value = "hesoyampharmacy+dragic@gmail.com")
    void testSalesReportYearlyAppointments() throws Exception {
        mvc.perform(get("/sales/appointment?type=YEARLY")
                .with(user(getUserDetails())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$..labels[0]").value("2020"))
                .andExpect(jsonPath("$..labels[1]").value("2021"));
    }

    private User getUserDetails(){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
