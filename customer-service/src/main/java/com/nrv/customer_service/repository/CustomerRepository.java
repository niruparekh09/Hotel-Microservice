package com.nrv.customer_service.repository;

import com.nrv.customer_service.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for Customer. It connects the service to customer database.
 *
 * @author Nirav Parekh
 * @see Customer
 * @since 1.0
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
