package com.nrv.customer_service.service.implementation;

import com.nrv.customer_service.exception.ResourceNotFoundException;
import com.nrv.customer_service.log.CustomerLogMessage;
import com.nrv.customer_service.model.Customer;
import com.nrv.customer_service.repository.CustomerRepository;
import com.nrv.customer_service.request.CustomerInsertionRequest;
import com.nrv.customer_service.response.APIResponse;
import com.nrv.customer_service.response.CustomerResponse;
import com.nrv.customer_service.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nrv.customer_service.service.implementation.CustomerServiceHelper.*;

/**
 * Service implementation class for Room. It contains the methods for service logic.
 *
 * @author Nirav Parekh
 * @see CustomerServiceHelper
 * @since 1.0
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerRepository repository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public List<CustomerResponse> fetchAllCustomer() {
        List<Customer> customerList = repository.findAll();
        List<CustomerResponse> responseList = customerListMapper(customerList);
        logger.info(CustomerLogMessage.CUSTOMER_LIST_GET.getMessage());
        return responseList;
    }

    @Override
    public CustomerResponse fetchACustomer(String customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID: " + customerId + " not found"));
        CustomerResponse response = getCustomerResponse(customer);
        logger.info(CustomerLogMessage.CUSTOMER_GET.getMessage(), customer.getCustomerId());
        return response;
    }

    @Override
    public CustomerResponse addACustomer(CustomerInsertionRequest newCustomer) {
        newCustomer.setPassword(encoder.encode(newCustomer.getPassword())); // Encoding Password
        Customer customer = getCustomer(newCustomer);
        Customer persistedCustomer = repository.save(customer);
        CustomerResponse response = getCustomerResponse(persistedCustomer);
        logger.info(CustomerLogMessage.CUSTOMER_ADD.getMessage(), persistedCustomer.getCustomerId());
        return response;
    }

    @Override
    public CustomerResponse updateCustomer(CustomerInsertionRequest updateCustomer, String customerId) {
        Customer existingCustomer = repository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID: " + customerId + " not found"));

        if (!updateCustomer.getPassword().isEmpty()) {
            updateCustomer.setPassword(encoder.encode(updateCustomer.getPassword())); // Encoding password
        }
        checkUpdate(updateCustomer, existingCustomer);

        Customer persistedCustomer = repository.save(existingCustomer);
        CustomerResponse response = getCustomerResponse(persistedCustomer);
        logger.info(CustomerLogMessage.CUSTOMER_UPDATE.getMessage(), existingCustomer.getCustomerId());
        return response;
    }

    @Override
    public APIResponse deleteCustomer(String customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID: " + customerId + " not found"));
        repository.delete(customer);
        logger.info(CustomerLogMessage.CUSTOMER_DELETE.getMessage(), customer.getCustomerId());
        return new APIResponse("Customer Deleted With ID: " + customer.getCustomerId());
    }
}
