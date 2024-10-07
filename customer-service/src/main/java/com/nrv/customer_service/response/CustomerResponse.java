package com.nrv.customer_service.response;

import com.nrv.customer_service.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Response object to provide client information about a customer.
 *
 * @author Nirav Parekh
 * @see com.nrv.customer_service.model.Customer
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String customerId;

    private String firstName;

    private String lastName;

    private String emailId;

    private String address;

    private LocalDate createdAt;

}
