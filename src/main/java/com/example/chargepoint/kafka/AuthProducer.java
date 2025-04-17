package com.example.chargepoint.kafka;

import com.example.chargepoint.model.AuthorizationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthProducer {
    private static final Logger logger = LoggerFactory.getLogger(AuthProducer.class);
    private final KafkaTemplate<String, AuthorizationRequest> kafkaTemplate;

    public AuthProducer(KafkaTemplate<String, AuthorizationRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(AuthorizationRequest request) {
        logger.info("Sending to Kafka: {}", request);
        kafkaTemplate.send("auth-topic", request);
    }
}