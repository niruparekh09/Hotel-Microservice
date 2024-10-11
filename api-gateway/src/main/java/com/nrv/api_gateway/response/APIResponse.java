package com.nrv.api_gateway.response;

import java.time.LocalDateTime;

/**
 * APIResponse class for common responses.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
public class APIResponse {
    private String message;
    private LocalDateTime time;

    public APIResponse(String message) {
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public APIResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
