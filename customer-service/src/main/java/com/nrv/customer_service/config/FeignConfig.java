package com.nrv.customer_service.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config class for Feign Client. Contains logic for ISC with Auth Service.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Configuration
public class FeignConfig {

    @Value("${API-KEY}")
    public String API_KEY;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-API-KEY", API_KEY);
        };
    }
}