package com.example.chargepoint.model;

import jakarta.validation.constraints.Size;

public class DriverIdentifier {
    @Size(min = 20, max = 80)
    private String id;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Override
    public String toString() {
        return "DriverIdentifier{id='" + id + "'}";
    }
}