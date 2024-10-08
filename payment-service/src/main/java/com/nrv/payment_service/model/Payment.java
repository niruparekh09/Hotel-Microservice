package com.nrv.payment_service.model;

import com.nrv.payment_service.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity class for Payment. Represents the fields present in Payment database. This will
 * be used to map values from database in from of an Object.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Document(collection = "payment") // Specify the collection name in MongoDB
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    private String paymentId;

    private String customerId;

    private Double amount;

    private Status paymentStatus;
}
