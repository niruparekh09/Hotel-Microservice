package com.nrv.auth_service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object to get data from client to insert information about a user.
 *
 * @author Nirav Parekh
 * @see com.nrv.auth_service.model.User
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInsertionRequest {
    @NotBlank(message = "User Id can not be blank")
    private String userId;

    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password should be at least 6 characters")
    private String password;
}
