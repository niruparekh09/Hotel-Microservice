package com.nrv.payment_service.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object to get client information about a payment.
 *
 * @author Nirav Parekh
 * @see com.nrv.payment_service.model.Payment
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String customerId;
    private double amount;
}
