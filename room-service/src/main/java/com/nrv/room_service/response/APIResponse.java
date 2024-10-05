package com.nrv.room_service.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * APIResponse object to provide client with common response
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
public class APIResponse {
    private String message;
    private LocalDateTime localDateTime;

    public APIResponse(String message) {
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }
}
