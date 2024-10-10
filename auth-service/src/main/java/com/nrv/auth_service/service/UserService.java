package com.nrv.auth_service.service;

import com.nrv.auth_service.request.UserInsertionRequest;
import com.nrv.auth_service.request.UserLoginRequest;
import com.nrv.auth_service.response.APIResponse;
import com.nrv.auth_service.response.UserResponse;

import java.util.List;

/**
 * Service interface for User. It contains the abstract methods for service logic.
 *
 * @author Nirav Parekh
 * @see com.nrv.auth_service.service.implementation.UserServiceImpl
 * @since 1.0
 */
public interface UserService {
    /**
     * Method to fetch all the users present in DB.
     *
     * @author Nirav Parekh
     * @see UserResponse
     * @since 1.0
     */
    List<UserResponse> getAllUsers();

    /**
     * Method to add a user in DB.
     *
     * @author Nirav Parekh
     * @see UserResponse
     * @since 1.0
     */
    UserResponse addAnUser(UserInsertionRequest newUser);

    /**
     * Method to a user in DB.
     *
     * @author Nirav Parekh
     * @see APIResponse
     * @since 1.0
     */
    APIResponse login(UserLoginRequest loginUser);

    /**
     * Method to update a user from DB.
     *
     * @author Nirav Parekh
     * @see UserResponse
     * @since 1.0
     */
    UserResponse updateAnUser(String userId, UserInsertionRequest updateUser);

    /**
     * Method to delete a user from DB.
     *
     * @author Nirav Parekh
     * @see APIResponse
     * @since 1.0
     */
    APIResponse deleteAnUser(String userId);


}
