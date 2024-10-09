package com.nrv.payment_service.controller;

import com.nrv.payment_service.request.PaymentRequest;
import com.nrv.payment_service.response.PaymentResponse;
import com.nrv.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for Payment API. Base API {@code /api/payment}
 *
 * @author Nirav Parekh
 * @see PaymentService
 * @since 1.0
 */
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentService service;

    /**
     * Method for {@code GET} request at {@code /api/payment}. The client will send request to get
     * all payments.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see PaymentResponse
     * @since 1.0
     */
    @GetMapping
    ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.fetchAllPayments());
    }

    /**
     * Method for {@code GET} request at {@code /api/payment/{id}}. The client will send request to get
     * a payment.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see PaymentResponse
     * @since 1.0
     */
    @GetMapping("/{id}")
    ResponseEntity<PaymentResponse> getAPayment(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.fetchPaymentByPaymentId(id));
    }

    /**
     * Method for {@code GET} request at {@code /api/payment/customer/{id}}. The client will send request to get
     * all payment by the customer.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see PaymentResponse
     * @since 1.0
     */
    @GetMapping("/customer/{id}")
    ResponseEntity<List<PaymentResponse>> getACustomerPayment(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.fetchPaymentsByCustomerId(id));
    }

    /**
     * Method for {@code POST} request at {@code /api/payment}. The client will send request to add
     * a payment of a customer.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see PaymentResponse
     * @since 1.0
     */
    @PostMapping
    ResponseEntity<PaymentResponse> addAPayment(@RequestBody PaymentRequest newPayment) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.addAPayment(newPayment));
    }
}
