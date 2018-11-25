package com.application.authservice.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    JwtAuthenticationResponse (@JsonProperty("accessToken") String accessToken ,@JsonProperty("tokenType") String tokenType) {

    }

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
