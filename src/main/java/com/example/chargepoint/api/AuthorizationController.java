package com.example.chargepoint.api;

import com.example.chargepoint.model.AuthorizationRequest;
import com.example.chargepoint.model.AuthorizationResponse;
import com.example.chargepoint.service.AuthorizationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthorizationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);
    private final AuthorizationService authService;

    public AuthorizationController(AuthorizationService authService) {
        this.authService = authService;
    }

    @PostMapping("/authorize")
    public ResponseEntity<AuthorizationResponse> authorize(@Valid @RequestBody AuthorizationRequest request) {
        logger.info("Received authorization request: {}", request);
        AuthorizationResponse response = authService.processAuthorization(request);
        logger.info("Authorization result: {}", response);
        return ResponseEntity.ok(response);
    }
}