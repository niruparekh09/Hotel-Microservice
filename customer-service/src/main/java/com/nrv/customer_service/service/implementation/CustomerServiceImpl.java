package com.nrv.customer_service.service.implementation;

import com.nrv.customer_service.client.AuthClient;
import com.nrv.customer_service.exception.ResourceNotFoundException;
import com.nrv.customer_service.log.CustomerLogMessage;
import com.nrv.customer_service.model.Customer;
import com.nrv.customer_service.repository.CustomerRepository;
import com.nrv.customer_service.request.CustomerInsertionRequest;
import com.nrv.customer_service.request.UserInsertionRequest;
import com.nrv.customer_service.response.APIResponse;
import com.nrv.customer_service.response.CustomerResponse;
import com.nrv.customer_service.service.CustomerService;
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
    AuthClient authClient;

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
        addCustomerToAuthService(persistedCustomer.getEmailId(), persistedCustomer.getPassword()); // Adding Customer to Auth Service
        CustomerResponse response = getCustomerResponse(persistedCustomer);
        logger.info(CustomerLogMessage.CUSTOMER_ADD.getMessage(), persistedCustomer.getCustomerId());
        return response;
    }

    public void addCustomerToAuthService(String emailId, String password) {
        UserInsertionRequest authUserInsertion = UserInsertionRequest.builder()
                .userId(emailId)
                .password(password)
                .build();
        logger.info("User Added To Auth Service With Id: {}", emailId);
        authClient.addAnAuthUser(authUserInsertion);
    }

    @Override
    public CustomerResponse updateCustomer(CustomerInsertionRequest updateCustomer, String customerId) {
        Customer existingCustomer = repository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID: " + customerId + " not found"));

        String previousEmail = existingCustomer.getEmailId(); // Saving previous mail for auth service before updation
        if (!updateCustomer.getPassword().isEmpty()) {
            updateCustomer.setPassword(encoder.encode(updateCustomer.getPassword())); // Encoding password
        }
        checkUpdate(updateCustomer, existingCustomer);
        Customer persistedCustomer = repository.save(existingCustomer);
        updateCustomerInAuthService(
                previousEmail,
                persistedCustomer.getEmailId(),
                persistedCustomer.getPassword()
        ); // Updating Customer in Auth Service Also
        CustomerResponse response = getCustomerResponse(persistedCustomer);
        logger.info(CustomerLogMessage.CUSTOMER_UPDATE.getMessage(), existingCustomer.getCustomerId());
        return response;
    }

    public void updateCustomerInAuthService(String previousEmail, String updatedEmailId, String password) {
        UserInsertionRequest authUserInsertion = UserInsertionRequest.builder()
                .userId(updatedEmailId)
                .password(password)
                .build();
        logger.info("User Updated In Auth Service With Id: {}", updatedEmailId);
        authClient.updateAnAuthUser(previousEmail, authUserInsertion);
    }

    @Override
    public APIResponse deleteCustomer(String customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID: " + customerId + " not found"));
        repository.delete(customer);
        deleteCustomerInAuthService(customer.getEmailId());
        // Deleting Customer from Auth Service
        logger.info(CustomerLogMessage.CUSTOMER_DELETE.getMessage(), customer.getCustomerId());
        return new APIResponse("Customer Deleted With ID: " + customer.getCustomerId());
    }

    public void deleteCustomerInAuthService(String email) {
        logger.info("User Deleted In Auth service With Email: {}", email);

        try {
            APIResponse apiResponse = authClient.deleteAnAuthUser(email);
            System.out.println(apiResponse);
        } catch (Exception e) {
            logger.error("e: ", e);
        }
    }
}
