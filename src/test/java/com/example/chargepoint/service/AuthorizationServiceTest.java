package com.example.chargepoint.service;

import com.example.chargepoint.kafka.AuthProducer;
import com.example.chargepoint.model.AuthorizationRequest;
import com.example.chargepoint.model.AuthorizationResponse;
import com.example.chargepoint.model.DriverIdentifier;
import com.example.chargepoint.whitelist.WhitelistStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorizationServiceTest {

    private AuthorizationService service;
    private WhitelistStore store;
    private AuthProducer producer;

    @BeforeEach
    void setup() {
        store = new WhitelistStore();
        store.init();
        producer = mock(AuthProducer.class);
        service = new AuthorizationService(store, producer);
    }

    private AuthorizationRequest createRequest(String id) {
        AuthorizationRequest req = new AuthorizationRequest();
        req.setStationUuid("station-1");
        DriverIdentifier di = new DriverIdentifier();
        di.setId(id);
        req.setDriverIdentifier(di);
        return req;
    }

    @Test
    void testAccepted() {
        AuthorizationResponse response = service.processAuthorization(createRequest("id12345678901234567890"));
        assertEquals("Accepted", response.getAuthorizationStatus());
    }

    @Test
    void testRejected() {
        AuthorizationResponse response = service.processAuthorization(createRequest("id09876543210987654321"));
        assertEquals("Rejected", response.getAuthorizationStatus());
    }

    @Test
    void testUnknown() {
        AuthorizationResponse response = service.processAuthorization(createRequest("id99999999999999999999"));
        assertEquals("Unknown", response.getAuthorizationStatus());
    }
}