package com.example.chargepoint.service;

import com.example.chargepoint.model.AuthorizationRequest;
import com.example.chargepoint.model.AuthorizationResponse;
import com.example.chargepoint.kafka.AuthProducer;
import com.example.chargepoint.whitelist.WhitelistStore;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final WhitelistStore whitelistStore;
    private final AuthProducer producer;

    public AuthorizationService(WhitelistStore whitelistStore, AuthProducer producer) {
        this.whitelistStore = whitelistStore;
        this.producer = producer;
    }

    public AuthorizationResponse processAuthorization(AuthorizationRequest request) {
        producer.send(request); // simulate async call
        String id = request.getDriverIdentifier().getId();
        if (!whitelistStore.exists(id)) return new AuthorizationResponse("Unknown");
        if (whitelistStore.isAllowed(id)) return new AuthorizationResponse("Accepted");
        return new AuthorizationResponse("Rejected");
    }
}