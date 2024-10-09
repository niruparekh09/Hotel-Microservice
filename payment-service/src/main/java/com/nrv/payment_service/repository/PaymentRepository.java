package com.nrv.payment_service.repository;

import com.nrv.payment_service.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findByCustomerId(String customerId);
}
