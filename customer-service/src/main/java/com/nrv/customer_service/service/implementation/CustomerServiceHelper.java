package com.nrv.customer_service.service.implementation;

import com.nrv.customer_service.model.Customer;
import com.nrv.customer_service.request.CustomerInsertionRequest;
import com.nrv.customer_service.response.CustomerResponse;

import java.time.LocalDate;
import java.util.List;

/**
 * Helper class for Customer Service.
 *
 * @author Nirav Parekh
 * @see CustomerServiceImpl
 * @since 1.0
 */
public class CustomerServiceHelper {

    public static List<CustomerResponse> customerListMapper(List<Customer> customerList) {
        return customerList.stream()
                .map(CustomerServiceHelper::getCustomerResponse)
                .toList();
    }

    public static CustomerResponse getCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .emailId(customer.getEmailId())
                .address(customer.getAddress())
                .createdAt(customer.getCreatedAt())
                .build();
    }

    public static Customer getCustomer(CustomerInsertionRequest newCustomer) {
        return Customer.builder()
                .firstName(newCustomer.getFirstName())
                .lastName(newCustomer.getLastName())
                .emailId(newCustomer.getEmailId())
                .address(newCustomer.getAddress())
                .createdAt(LocalDate.now())
                .password(newCustomer.getPassword())
                .build();
    }

    public static void checkUpdate(CustomerInsertionRequest updateCustomer, Customer existingCustomer) {
        // Update fields selectively
        if (!updateCustomer.getFirstName().isEmpty()) {
            existingCustomer.setFirstName(updateCustomer.getFirstName());
        }
        if (!updateCustomer.getLastName().isEmpty()) {
            existingCustomer.setLastName(updateCustomer.getLastName());
        }
        if (!updateCustomer.getEmailId().isEmpty()) {
            existingCustomer.setEmailId(updateCustomer.getEmailId());
        }
        if (!updateCustomer.getPassword().isEmpty()) {
            existingCustomer.setPassword(updateCustomer.getPassword());
        }
        if (!updateCustomer.getAddress().isEmpty()) {
            existingCustomer.setAddress(updateCustomer.getAddress());
        }
    }
}
