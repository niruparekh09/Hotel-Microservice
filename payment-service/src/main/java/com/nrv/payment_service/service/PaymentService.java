package com.nrv.payment_service.service;

import com.nrv.payment_service.request.PaymentRequest;
import com.nrv.payment_service.response.PaymentResponse;

import java.util.List;

/**
 * Service interface for Payment. It contains the abstract methods for service logic.
 *
 * @author Nirav Parekh
 * @see com.nrv.payment_service.service.implementation.PaymentServiceImpl
 * @since 1.0
 */
public interface PaymentService {

    /**
     * Method to fetch all the payments present in DB.
     *
     * @author Nirav Parekh
     * @see PaymentResponse
     * @since 1.0
     */
    List<PaymentResponse> fetchAllPayments();

    /**
     * Method to fetch all the payments present in DB with that customer ID.
     *
     * @param customerId Customer ID
     * @author Nirav Parekh
     * @see PaymentResponse
     * @since 1.0
     */
    List<PaymentResponse> fetchPaymentsByCustomerId(String customerId);

    /**
     * Method to fetch a payment with payment ID.
     *
     * @param paymentId Payment ID
     * @author Nirav Parekh
     * @see PaymentResponse
     * @since 1.0
     */
    PaymentResponse fetchPaymentByPaymentId(String paymentId);

    /**
     * Method to add a payment (to do a payment).
     *
     * @param paymentRequest Payment Request
     * @author Nirav Parkeh
     * @see PaymentResponse
     * @see PaymentRequest
     * @since 1.0
     */
    PaymentResponse addAPayment(PaymentRequest paymentRequest);

}
