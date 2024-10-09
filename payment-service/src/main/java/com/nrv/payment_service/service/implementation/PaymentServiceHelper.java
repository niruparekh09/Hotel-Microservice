package com.nrv.payment_service.service.implementation;

import com.nrv.payment_service.model.Payment;
import com.nrv.payment_service.model.enums.Status;
import com.nrv.payment_service.request.PaymentRequest;
import com.nrv.payment_service.response.PaymentResponse;

import java.util.List;

public class PaymentServiceHelper {
    public static List<PaymentResponse> paymentListMapper(List<Payment> paymentList) {
        return paymentList.stream()
                .map(PaymentServiceHelper::getPaymentResponse)
                .toList();
    }

    public static PaymentResponse getPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .customerId(payment.getCustomerId())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus().name())
                .build();
    }

    public static Payment getPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .customerId(paymentRequest.getCustomerId())
                .amount(paymentRequest.getAmount())
                .paymentStatus(Status.SUCCESS) // Not holding the payment as we have not implemented payment gateway yet
                .build();
    }
}
