package com.hesoyam.pharmacy.integration.feedback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hesoyam.pharmacy.feedback.dto.ReplyDTO;
import com.hesoyam.pharmacy.user.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ReplyTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

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
    @WithUserDetails(value="hesoyampharmacy+admin@gmail.com")
    void replyComplaintTest() throws Exception {
        User userDetails = getUserDetails();
        mvc.perform(post("/complaint/reply").contentType(MediaType.APPLICATION_JSON_VALUE).content(mockReplyDTO(1l)).with(user(userDetails))).andExpect(status().is2xxSuccessful());
        mvc.perform(post("/complaint/reply").contentType(MediaType.APPLICATION_JSON_VALUE).content(mockReplyDTO(2l)).with(user(userDetails))).andExpect(status().is(400));
    }

    private String mockReplyDTO(Long complaintId) throws JsonProcessingException {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setText("My first reply.");
        replyDTO.setComplaintId(complaintId);
        return objectWriter.writeValueAsString(replyDTO);
    }

    private User getUserDetails(){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal ();
    }
}
