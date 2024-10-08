package com.nrv.auth_service.service.implementation;

import com.nrv.auth_service.model.User;
import com.nrv.auth_service.model.enums.Role;
import com.nrv.auth_service.request.UserInsertionRequest;
import com.nrv.auth_service.response.UserResponse;

import java.util.List;

/**
 * Helper class for User Service.
 *
 * @author Nirav Parekh
 * @see UserServiceImpl
 * @since 1.0
 */
public class UserServiceHelper {

    public static List<UserResponse> userListMapper(List<User> userList) {
        return userList.stream()
                .map(UserServiceHelper::getUserResponse)
                .toList();
    }

    public static UserResponse getUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .role(user.getRole().name())
                .build();
    }

    public static User getUser(UserInsertionRequest newUser) {
        return User.builder()
                .userId(newUser.getUserId())
                .password(newUser.getPassword())
                .role(Role.ROLE_USER) // We can only add user not admin
                .build();
    }
}
