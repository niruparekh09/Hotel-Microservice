package com.nrv.payment_service.service.implementation;

import com.nrv.payment_service.exception.ResourceNotFoundException;
import com.nrv.payment_service.log.PaymentLogMessage;
import com.nrv.payment_service.model.Payment;
import com.nrv.payment_service.repository.PaymentRepository;
import com.nrv.payment_service.request.PaymentRequest;
import com.nrv.payment_service.response.PaymentResponse;
import com.nrv.payment_service.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nrv.payment_service.service.implementation.PaymentServiceHelper.*;

/**
 * Service implementation class for Payment. It contains the methods for service logic.
 *
 * @author Nirav Parekh
 * @see PaymentServiceHelper
 * @since 1.0
 */
@Slf4j
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public List<PaymentResponse> fetchAllPayments() {
        List<Payment> paymentList = paymentRepository.findAll();
        List<PaymentResponse> paymentResponses = paymentListMapper(paymentList);
        log.info(PaymentLogMessage.PAYMENT_LIST_GET.getMessage());
        return paymentResponses;
    }

    @Override
    public List<PaymentResponse> fetchPaymentsByCustomerId(String customerId) {
        List<Payment> paymentList = paymentRepository.findByCustomerId(customerId);
        List<PaymentResponse> paymentResponses = paymentListMapper(paymentList);
        log.info(PaymentLogMessage.PAYMENT_GET_BY_CUSTOMER.getMessage(), paymentList.get(0).getCustomerId());
        return paymentResponses;
    }

    @Override
    public PaymentResponse fetchPaymentByPaymentId(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found by ID: " + paymentId));
        PaymentResponse paymentResponse = getPaymentResponse(payment);
        log.info(PaymentLogMessage.PAYMENT_GET.getMessage(), payment.getPaymentId());
        return paymentResponse;
    }

    @Override
    public PaymentResponse addAPayment(PaymentRequest paymentRequest) {
        Payment payment = getPayment(paymentRequest);
        Payment save = paymentRepository.save(payment);
        PaymentResponse paymentResponse = getPaymentResponse(save);
        log.info(PaymentLogMessage.PAYMENT_ADD.getMessage(), save.getPaymentId());
        return paymentResponse;
    }
}
