package com.nrv.auth_service.controller;

import com.nrv.auth_service.request.UserInsertionRequest;
import com.nrv.auth_service.response.APIResponse;
import com.nrv.auth_service.response.UserResponse;
import com.nrv.auth_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for User API. Base API {@code /api/auth}
 *
 * @author Nirav Parekh
 * @see UserService
 * @since 1.0
 */
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserService service;

    /**
     * Method for {@code GET} request at {@code /api/auth}. The client will send request to get all authenticated users.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see UserResponse
     * @since 1.0
     */
    @GetMapping
    ResponseEntity<List<UserResponse>> getAllAuthUser() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllUsers());
    }

    /**
     * Method for {@code POST} request at {@code /api/auth}. The client will be able
     * to request to add a new auth user.
     *
     * @param newAuthUser New Auth User
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see UserResponse
     * @see UserInsertionRequest
     * @since 1.0
     */
    @PostMapping("/register")
    ResponseEntity<UserResponse> addAnAuthUser(@RequestBody @Valid UserInsertionRequest newAuthUser) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.addAnUser(newAuthUser));
    }

    /**
     * Method for {@code PUT} request at {@code /api/auth/{id}}. The client will be able
     * to request to update an auth client.
     *
     * @param updateAuthUser Update Auth User
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see UserResponse
     * @see UserInsertionRequest
     * @since 1.0
     */
    @PutMapping("/{id}")
    ResponseEntity<UserResponse>
    updateAnAuthUser(@PathVariable String id,
                     @RequestBody @Valid UserInsertionRequest updateAuthUser) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateAnUser(id, updateAuthUser));
    }

    /**
     * Method for {@code DELETE} request at {@code /api/auth/{id}}. The client will be able
     * to request to delete an auth client.
     *
     * @return ResponseEntity with the operation result.
     * @author Nirav Parekh
     * @see APIResponse
     * @since 1.0
     */
    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse> deleteAnAuthUser(@PathVariable String id) {
        System.out.println(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.deleteAnUser(id));
    }
}
