package com.nrv.booking_service.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class APIResponse {
    private String message;
    private LocalDateTime localDateTime;

    public APIResponse(String message) {
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }
}
