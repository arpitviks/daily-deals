package com.dailyDeals.dailyDeals_v6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AuthResponse {
    private final String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}