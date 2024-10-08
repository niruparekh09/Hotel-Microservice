package com.nrv.customer_service.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Client Interface for inter service communication with Auth-Service
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@FeignClient(name = "AUTH-SERVICE")
public interface AuthClient {
}
