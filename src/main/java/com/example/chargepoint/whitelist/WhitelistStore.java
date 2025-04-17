package com.example.chargepoint.whitelist;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WhitelistStore {
    private final Map<String, Boolean> whitelist = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        whitelist.put("id12345678901234567890", true);
        whitelist.put("id09876543210987654321", false);
    }

    public boolean exists(String id) {
        return whitelist.containsKey(id);
    }

    public boolean isAllowed(String id) {
        return whitelist.getOrDefault(id, false);
    }
}
