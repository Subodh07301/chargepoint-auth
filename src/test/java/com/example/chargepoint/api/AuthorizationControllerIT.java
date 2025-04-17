package com.example.chargepoint.api;

import com.example.chargepoint.model.AuthorizationRequest;
import com.example.chargepoint.model.DriverIdentifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAuthorizeAccepted() throws Exception {
        AuthorizationRequest req = new AuthorizationRequest();
        req.setStationUuid("uuid-test");
        DriverIdentifier di = new DriverIdentifier();
        di.setId("id12345678901234567890");
        req.setDriverIdentifier(di);

        mockMvc.perform(post("/api/authorize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorizationStatus").value("Accepted"));
    }

    @Test
    void testAuthorizeRejected() throws Exception {
        AuthorizationRequest req = new AuthorizationRequest();
        req.setStationUuid("uuid-test");
        DriverIdentifier di = new DriverIdentifier();
        di.setId("id09876543210987654321");
        req.setDriverIdentifier(di);

        mockMvc.perform(post("/api/authorize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorizationStatus").value("Rejected"));
    }

    @Test
    void testAuthorizeUnknown() throws Exception {
        AuthorizationRequest req = new AuthorizationRequest();
        req.setStationUuid("uuid-test");
        DriverIdentifier di = new DriverIdentifier();
        di.setId("id99999999999999999999");
        req.setDriverIdentifier(di);

        mockMvc.perform(post("/api/authorize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorizationStatus").value("Unknown"));
    }

    @Test
    void testValidationFailure() throws Exception {
        AuthorizationRequest req = new AuthorizationRequest();
        req.setStationUuid("");
        DriverIdentifier di = new DriverIdentifier();
        di.setId("short");
        req.setDriverIdentifier(di);

        mockMvc.perform(post("/api/authorize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }
}
