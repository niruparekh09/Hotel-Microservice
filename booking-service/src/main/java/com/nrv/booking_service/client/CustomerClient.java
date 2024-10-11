package com.nrv.booking_service.client;

import com.nrv.booking_service.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Client Interface for inter service communication with Customer-Service
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerClient {
    @GetMapping("/api/customer/{id}")
    CustomerResponse getACustomer(@PathVariable String id);
}
