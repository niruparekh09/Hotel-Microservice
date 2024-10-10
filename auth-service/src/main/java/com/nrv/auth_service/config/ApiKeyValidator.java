package com.nrv.auth_service.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Validator class to intercept request with other service.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Component("apiKeyValidator")
public class ApiKeyValidator {

    @Value("${API-KEY}")
    public String API_KEY;// Same key used by Customer-Service

    public boolean validate(HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-KEY");
        return API_KEY.equals(apiKey);
    }
}
