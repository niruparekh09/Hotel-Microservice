package com.nrv.customer_service.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Request object to get data from client to insert information about a customer.
 *
 * @author Nirav Parekh
 * @see com.nrv.customer_service.model.Customer
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInsertionRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email ID is required")
    @Email(message = "Email ID should be valid")
    @Indexed(unique = true)
    private String emailId;

    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password should be at least 6 characters")
    private String password;

    private String address;
}
