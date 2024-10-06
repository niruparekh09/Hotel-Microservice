package com.nrv.customer_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for Customer. Represents the fields present in Customer database. This will
 * be used to map values from database in from of an Object.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Document(collection = "customer") // Specify the collection name in MongoDB
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    private String customerId;

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

    private LocalDate createdAt;

    private List<Booking> bookingList = new ArrayList<>();
}
