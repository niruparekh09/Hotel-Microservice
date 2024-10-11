package com.nrv.customer_service.service;

import com.nrv.customer_service.request.CustomerInsertionRequest;
import com.nrv.customer_service.response.APIResponse;
import com.nrv.customer_service.response.CustomerResponse;

import java.util.List;

/**
 * Service interface for Customer. It contains the abstract methods for service logic.
 *
 * @author Nirav Parekh
 * @see com.nrv.customer_service.service.implementation.CustomerServiceImpl
 * @since 1.0
 */
public interface CustomerService {
    /**
     * Method to fetch all the customer present in DB.
     *
     * @author Nirav Parekh
     * @see CustomerResponse
     * @since 1.0
     */
    List<CustomerResponse> fetchAllCustomer();

    /**
     * Method to fetch a customer present in DB with that ID.
     *
     * @param customerId Customer id
     * @author Nirav Parekh
     * @see CustomerResponse
     * @since 1.0
     */
    CustomerResponse fetchACustomer(String customerId);

    /**
     * Method to add a new Customer.
     *
     * @param newCustomer New Customer
     * @author Nirav Parekh
     * @see CustomerInsertionRequest
     * @since 1.0
     */
    CustomerResponse addACustomer(CustomerInsertionRequest newCustomer);

    /**
     * Method to update a customer.
     *
     * @param updateRoom   update room
     * @param customerId   id of customer to be updated
     * @param loggedInUser Logged in user
     * @author Nirav Parekh
     * @see CustomerInsertionRequest
     * @since 1.0
     */
    CustomerResponse updateCustomer(CustomerInsertionRequest updateRoom, String customerId, String loggedInUser);

    /**
     * Method to delete a customer information.
     *
     * @param customerId Customer ID
     * @author Nirav Parekh
     * @see APIResponse
     * @since 1.0
     */
    APIResponse deleteCustomer(String customerId);
}
