package com.example.chargepoint.model;

public class AuthorizationResponse {
    private String authorizationStatus;

    public AuthorizationResponse(String authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }

    public String getAuthorizationStatus() { return authorizationStatus; }
    public void setAuthorizationStatus(String authorizationStatus) { this.authorizationStatus = authorizationStatus; }

    @Override
    public String toString() {
        return "AuthorizationResponse{authorizationStatus='" + authorizationStatus + "'}";
    }
}