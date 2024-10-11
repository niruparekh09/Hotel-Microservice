package com.nrv.customer_service.controller;

import com.nrv.customer_service.request.CustomerInsertionRequest;
import com.nrv.customer_service.response.APIResponse;
import com.nrv.customer_service.response.CustomerResponse;
import com.nrv.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for Customer API. Base API {@code /api/customer}
 *
 * @author Nirav Parekh
 * @see CustomerService
 * @since 1.0
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    /**
     * Method for {@code GET} request at {@code /api/customer}. The client will send request to get
     * all customers.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see CustomerResponse
     * @since 1.0
     */
    @GetMapping
    ResponseEntity<List<CustomerResponse>> getAllCustomer() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.fetchAllCustomer());
    }

    /**
     * Method for {@code GET} request at {@code /api/customer/{id}}. The client will send request to get
     * a customer.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see CustomerResponse
     * @since 1.0
     */
    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getACustomer(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.fetchACustomer(id));
    }

    /**
     * Method for {@code POST} request at {@code /api/customer}. The client will be able
     * to request to add a customer.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see CustomerResponse
     * @see CustomerInsertionRequest
     * @since 1.0
     */
    @PostMapping()
    ResponseEntity<CustomerResponse>
    addACustomer(@RequestBody @Valid CustomerInsertionRequest newCustomer) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.addACustomer(newCustomer));
    }

    /**
     * Method for {@code PUT} request at {@code /api/customer/{id}}. The client will be able
     * to request to update a customer.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see CustomerResponse
     * @since 1.0
     */
    @PutMapping("/{id}")
    ResponseEntity<CustomerResponse>
    updateACustomer(@PathVariable String id,
                    @RequestBody @Valid CustomerInsertionRequest updateCustomer,
                    @RequestHeader("loggedInUser") String loggedInUser) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateCustomer(updateCustomer, id, loggedInUser));
    }

    /**
     * Method for {@code DELETE} request at {@code /api/customer/{id}}. The client will be able
     * to request to update a customer.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see APIResponse
     * @since 1.0
     */
    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse> deleteACustomer(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.deleteCustomer(id));
    }

}
