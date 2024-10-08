package com.nrv.auth_service.model;

import com.nrv.auth_service.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity class for User. Represents the fields present in User database. This will
 * be used to map values from database in from of an Object.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Document(collection = "user") // Specify the collection name in MongoDB
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "User Id is required")
    private String userId;

    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password should be at least 6 characters")
    private String password;

    @NotBlank(message = "Role is required")
    private Role role;
}
