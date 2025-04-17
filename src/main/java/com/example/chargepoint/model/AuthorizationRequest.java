package com.example.chargepoint.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthorizationRequest {
    @NotBlank
    private String stationUuid;

    @NotNull
    private DriverIdentifier driverIdentifier;

    // Getters and setters
    public String getStationUuid() { return stationUuid; }
    public void setStationUuid(String stationUuid) { this.stationUuid = stationUuid; }
    public DriverIdentifier getDriverIdentifier() { return driverIdentifier; }
    public void setDriverIdentifier(DriverIdentifier driverIdentifier) { this.driverIdentifier = driverIdentifier; }

    @Override
    public String toString() {
        return "AuthorizationRequest{" +
                "stationUuid='" + stationUuid + '\'' +
                ", driverIdentifier=" + driverIdentifier +
                '}';
    }
}