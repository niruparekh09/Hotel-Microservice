package com.nrv.booking_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Response object to provide client information about a customer.
 *
 * @author Nirav Parekh
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

