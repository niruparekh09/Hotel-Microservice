package com.nrv.auth_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response object to provide client information about a user.
 *
 * @author Nirav Parekh
 * @see com.nrv.auth_service.model.User
 * @see com.nrv.auth_service.model.enums.Role
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String userId;
    private String role;
}
