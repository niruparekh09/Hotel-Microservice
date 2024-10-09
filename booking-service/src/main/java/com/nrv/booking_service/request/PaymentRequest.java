package com.nrv.booking_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object to get client information about a payment.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    private String customerId;

    private double amount;
}
