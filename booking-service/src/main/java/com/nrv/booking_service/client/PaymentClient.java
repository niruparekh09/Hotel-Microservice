package com.nrv.booking_service.client;

import com.nrv.booking_service.request.PaymentRequest;
import com.nrv.booking_service.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Client Interface for inter service communication with Payment-Service
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentClient {
    @PostMapping("/api/payment")
    PaymentResponse addAPayment(@RequestBody PaymentRequest newPayment);
}
