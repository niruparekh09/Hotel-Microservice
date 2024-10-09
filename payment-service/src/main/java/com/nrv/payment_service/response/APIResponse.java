package com.nrv.payment_service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {
    private String message;
    private LocalDateTime localDateTime;

    public APIResponse(String message) {
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }
}
