package com.nrv.booking_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response object to provide client information about a payment.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private String paymentId;

    private String customerId;

    private Double amount;

    private String paymentStatus;
}
